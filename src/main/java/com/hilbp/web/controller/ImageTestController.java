package com.hilbp.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.bytedeco.javacpp.DoublePointer;
import org.bytedeco.javacpp.indexer.FloatIndexer;
import org.bytedeco.opencv.global.opencv_core;
import org.bytedeco.opencv.global.opencv_highgui;
import org.bytedeco.opencv.global.opencv_imgcodecs;
import org.bytedeco.opencv.global.opencv_imgproc;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Point;
import org.bytedeco.opencv.opencv_core.Rect;
import org.bytedeco.opencv.opencv_core.Scalar;
import org.bytedeco.opencv.opencv_core.Size;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/image")
@Slf4j
public class ImageTestController {
	
	
	
	//测试xml
	@RequestMapping("/index")
	public ResponseEntity<Object> index() {
		log.info("start");
		String[] args = new String[2];
		args[0] = "log/screen.png";
		args[1] = "log/1.png";
		newStyle(args);
        //oldStyle(args);
		return null;
	}
	
	
	public void newStyle(String[] args){
		
        //read in image default colors
        Mat sourceColor = opencv_imgcodecs.imread(args[0]);
        Mat sourceGrey = new Mat(sourceColor.size(), opencv_core.CV_8UC1);
        opencv_imgproc.cvtColor(sourceColor, sourceGrey, opencv_imgproc.COLOR_BGR2GRAY);
       
       //load in template in grey 
       Mat template = opencv_imgcodecs.imread(args[1], opencv_imgcodecs.IMREAD_GRAYSCALE);//int = 0
       
       //Size for the result image
       Size size = new Size(sourceGrey.cols()-template.cols()+1, sourceGrey.rows()-template.rows()+1);
       Mat result = new Mat(size, opencv_core.CV_32FC1);
       opencv_imgproc.matchTemplate(sourceGrey, template, result, opencv_imgproc.TM_CCORR_NORMED);
//       opencv_imgproc.threshold(src, dst, thresh, maxval, ThresholdTypes.Tozero);
//       opencv_imgproc.floodFill(image, seedPoint, newVal)
       
       DoublePointer minVal= new DoublePointer();
       DoublePointer maxVal= new DoublePointer();
       Point min = new Point();
       Point max = new Point();
       opencv_core.minMaxLoc(result, minVal, maxVal, min, max, null);
//       log.info("[{}, {}]", max.x(), max.y());
//       opencv_imgproc.rectangle(sourceColor,new Rect(max.x(),max.y(),template.cols(),template.rows()), randColor(), 2, 0, 0);
       
       int centerWith = template.cols() / 2;
       int centerHeight = template.rows() / 2;
       getPointsFromMatAboveThreshold(result, 0.9999f).stream().forEach((point) -> {
    	   log.info("[{}, {}]", point.x(), point.y());
    	   log.info("[{}, {}]", point.x() + centerWith, point.y() + centerHeight);
    	   opencv_imgproc.rectangle(sourceColor, new Rect(point.x(), point.y(), template.cols(), template.rows()), randColor(), 2, 0, 0);
       });

//       List<Point> points = this.getPointsFromMatAboveThreshold(result, 0.99f);
//       for(Point point : points) {
//    	   opencv_imgproc.rectangle(sourceColor,new Rect(point.x(), point.y(), 30, 30), randColor(), 2, 0, 0);
//    	   
//       }
       opencv_highgui.imshow("Original marked", sourceColor);
//       imshow("Ttemplate", template);
//       imshow("Results matrix", result);
       
       opencv_imgcodecs.imwrite("log/res.png", sourceColor);
       
       opencv_highgui.waitKey(0);
       
       
       opencv_highgui.destroyAllWindows();
        
    }

    // some usefull things.
    public Scalar randColor(){
       int b,g,r;
       b= ThreadLocalRandom.current().nextInt(0, 255 + 1);
       g= ThreadLocalRandom.current().nextInt(0, 255 + 1);
       r= ThreadLocalRandom.current().nextInt(0, 255 + 1);
       return new Scalar (b,g,r,0);
    }
    
    public List<Point> getPointsFromMatAboveThreshold(Mat m, float t){
    	
    	List<Point> matches = new ArrayList<Point>();
    	FloatIndexer indexer = m.createIndexer();
    	for (int y = 0; y < m.rows(); y++) {
			for (int x = 0; x < m.cols(); x++) {
				if (indexer.get(y,x) > t) {
					//System.out.println("(" + x + "," + y +") = "+ indexer.get(y,x));
					matches.add(new Point(x, y));
	   
				}
			}
    	}
    	return matches;
    }
	
}
