package com.hilbp.web.controller;


import org.bytedeco.opencv.global.opencv_imgcodecs;
import org.bytedeco.opencv.global.opencv_imgproc;
import org.bytedeco.opencv.opencv_core.Mat;
import org.opencv.core.CvType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/img")
@Slf4j
public class ImgTestController {
	
	
	
	//测试xml
	@RequestMapping("/index")
	public ResponseEntity<Object> index() {

		smooth();
		return null;
	}
	
	public static void smooth() {
		
		String filename = "log/screen.png";
        @SuppressWarnings("static-access")
		Mat image = opencv_imgcodecs.imread(filename);
        if (image != null) {
        	
//            GaussianBlur(image, image, new Size(3, 3), 0);
//            imwrite(filename, image);
        	System.out.println(image.size().width());
        }
        
       
        
    }
	
	public void run(String inFile, String templateFile, String outFile, int match_method) {
		
	    System.out.println("Running Template Matching");
	    

	    Mat img = opencv_imgcodecs.imread(inFile);
	    Mat templ = opencv_imgcodecs.imread(templateFile);

	    // / Create the result matrix
	    int result_cols = img.cols() - templ.cols() + 1;
	    int result_rows = img.rows() - templ.rows() + 1;
	    Mat result = new Mat(result_rows, result_cols, CvType.CV_32FC1);
	    

	    // / Do the Matching and Normalize
	    opencv_imgproc.matchTemplate(img, templ, result, match_method);
	    
	    // Core.normalize(result, result, 0, 1, Core.NORM_MINMAX, -1, new
	    // Mat());
	    opencv_imgproc.threshold(result, result, 0.1, 1.0, opencv_imgproc.THRESH_TOZERO);
	   
	    
//	    // / Localizing the best match with minMaxLoc
//	    MinMaxLocResult mmr = Core.minMaxLoc(result);
//	    
//
//	    Point matchLoc;
//	    if (match_method == Imgproc.TM_SQDIFF
//	            || match_method == Imgproc.TM_SQDIFF_NORMED) {
//	        matchLoc = mmr.minLoc;
//	    } else {
//	        matchLoc = mmr.maxLoc;
//	    }
	    
//	    double threashhold = 1.0;
//	    if (mmr.maxVal > threashhold) {
//	        System.out.println(matchLoc.x() +" "+matchLoc.y());  
//	        opencv_imgproc.rectangle(img, matchLoc, new Point(matchLoc.x() + templ.cols(),
//	                matchLoc.y() + templ.rows()), new Scalar(0, 255, 0, 0));
//	    }
//	    
	    // Save the visualized detection.
	    opencv_imgcodecs.imwrite(outFile, img);
		    
	}
}
