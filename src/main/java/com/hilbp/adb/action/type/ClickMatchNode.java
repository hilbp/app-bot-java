package com.hilbp.adb.action.type;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.bytedeco.javacpp.DoublePointer;
import org.bytedeco.javacpp.indexer.FloatIndexer;
import org.bytedeco.opencv.global.opencv_core;
import org.bytedeco.opencv.global.opencv_imgcodecs;
import org.bytedeco.opencv.global.opencv_imgproc;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Point;
import org.bytedeco.opencv.opencv_core.Rect;
import org.bytedeco.opencv.opencv_core.Scalar;
import org.bytedeco.opencv.opencv_core.Size;
import org.springframework.stereotype.Component;

import com.hilbp.adb.action.type.base.ActionType;
import com.hilbp.adb.entity.Action;
import com.hilbp.adb.entity.Coord;
import com.hilbp.adb.entity.Result;
import com.hilbp.adb.util.UiAutoMatorUtil;

import lombok.extern.slf4j.Slf4j;
import se.vidstige.jadb.JadbDevice;

/**
 * 截屏，然后根据控件图标利用javacv匹配图标
 * 1.截屏的保存位置
 * 2.目标图的保存位置
 * 3.匹配结果的保存位置
 * 4.阈值设置
 * @author hilbp
 *
 */
@Component
@Slf4j
public class ClickMatchNode extends ActionType {
	
	@Override
	public void operate(JadbDevice device, Action action, Result resutl) {}
	
	@Override
	public void operate(JadbDevice device, Action action) {
		this.run(device, action);
	}
	
	public void run(JadbDevice device, Action action) {
		String sourcePath = action.getSourcePath();
		
		//先截屏
		this.beforExecuteShell(device, action);
		adbShellUtil.getScreenshot(device, sourcePath);
		this.afterExecuteShell(device, action);
		
		//匹配及点击
		String templatePath = action.getTemplatePath();
		List<Coord> coords = match(sourcePath, templatePath);
		this.click(device, action, coords);
	}

	public List<Coord> match(String sourcePath, String templatePath){
		
		//read in image default colors
        Mat sourceColor = opencv_imgcodecs.imread(sourcePath);
        Mat sourceGrey = new Mat(sourceColor.size(), opencv_core.CV_8UC1);
        opencv_imgproc.cvtColor(sourceColor, sourceGrey, opencv_imgproc.COLOR_BGR2GRAY);
       
        //load in template in grey 
        Mat template = opencv_imgcodecs.imread(templatePath, opencv_imgcodecs.IMREAD_GRAYSCALE);//int = 0
       
        //Size for the result image
	    Size size = new Size(sourceGrey.cols() - template.cols() + 1, sourceGrey.rows() - template.rows() + 1);
        Mat result = new Mat(size, opencv_core.CV_32FC1);
        opencv_imgproc.matchTemplate(sourceGrey, template, result, opencv_imgproc.TM_CCORR_NORMED);
       
        List<Coord> coords = new ArrayList<Coord>();
        getPointsFromMatAboveThreshold(result, 0.9999f).stream().forEach((point) -> {
        	
        	opencv_imgproc.rectangle(sourceColor, new Rect(point.x(), point.y(), template.cols(), template.rows()), randColor(), 2, 0, 0);
        	Coord coord = new Coord();
        	int x = UiAutoMatorUtil.getRandom(point.x() + template.cols() - 5, point.x() + 5);
        	int y = UiAutoMatorUtil.getRandom(point.y() + template.rows() - 5, point.y() + 5);
        	coord.setX(x);
        	coord.setY(y);
        	coords.add(coord);
        	log.info("→   [{}, {}]", x, y);
        });
        opencv_imgcodecs.imwrite("log/res.png", sourceColor);
        
        return coords;
    }

    //some usefull things. 
    public Scalar randColor(){
		int b,g,r;
	    b = ThreadLocalRandom.current().nextInt(0, 255 + 1);
	    g = ThreadLocalRandom.current().nextInt(0, 255 + 1);
	    r = ThreadLocalRandom.current().nextInt(0, 255 + 1);
	    return new Scalar (b, g, r, 0);
    }
    
    //多目标匹配
    public List<Point> getPointsFromMatAboveThreshold(Mat m, float t){
    	List<Point> matches = new ArrayList<Point>();
    	FloatIndexer indexer = m.createIndexer();
    	for (int y = 0; y < m.rows(); y++) {
			for (int x = 0; x < m.cols(); x++) {
				if (indexer.get(y, x) > t) {
					matches.add(new Point(x, y));
				}
			}
    	}
    	return matches;
    }
    
    //最佳匹配
    public Coord bestTarget(Mat m) {

    	 DoublePointer minVal= new DoublePointer();
         DoublePointer maxVal= new DoublePointer();
         Point min = new Point();
         Point max = new Point();
         opencv_core.minMaxLoc(m, minVal, maxVal, min, max, null);
         return new Coord(max.x(), max.y());
    }
}
