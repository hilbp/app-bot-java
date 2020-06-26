package com.hilbp.web.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hilbp.adb.util.AdbShellUtil;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/image")
@Slf4j
public class ImageTestController {
	
	@Autowired
	AdbShellUtil adbShellUtil;
	
	
	//测试xml
	@RequestMapping("/index")
	public ResponseEntity<Object> index() {
		
		String shell = "adb shell screencap -p";
		try {
			Process res = Runtime.getRuntime().exec(shell);
			String str = adbShellUtil.inputStreamToString(res.getInputStream());
			File file = new File("E:/adb/tar.png");
			OutputStream output = new FileOutputStream("out/readme.txt");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	
}
