package com.hilbp.web.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSON;
import com.hilbp.adb.entity.SocketInput;
import com.hilbp.adb.util.AdbShellUtil;

import se.vidstige.jadb.JadbDevice;

@Controller
public class ScreenMirrorController {
	
	@Autowired
    private SimpMessagingTemplate messagingTemplate;
	
	@Autowired
	private AdbShellUtil adbShellUtil;

	/**
	 * 投屏测试
	 */
	@MessageMapping("/screen1")
	public void screen(SocketInput input) throws Exception {
		
		JadbDevice device = adbShellUtil.getDevices().get(0);	
		String savePath = "src/main/resources/static/assert/screen.png";
		adbShellUtil.getScreenshot(device, savePath, true);
		messagingTemplate.convertAndSend("/topic/screen", "assert/screen.png");
	}
	
	/**
	 * 投屏测试1
	 */
	@MessageMapping("/screen")
	public void screen1(SocketInput input) throws Exception {
		
    	String cmd = "adb exec-out screencap -p";
    	try {
			Process process = Runtime.getRuntime().exec(cmd);
			InputStream inputStream = process.getInputStream();
			
			ByteArrayOutputStream swapStream = new ByteArrayOutputStream(); 
			byte[] buff = new byte[2048];
			int rc = 0; 

			while ((rc = inputStream.read(buff, 0, 100)) > 0) { 
				swapStream.write(buff, 0, rc); 
			} 
			byte[] bin = swapStream.toByteArray();
//			GenericMessage<byte[]> message = new GenericMessage<byte[]>(bin);
//			messagingTemplate.convertAndSend("/topic/screen", JSON.toJSON(message));
//			messagingTemplate.send("/topic/screen", message);
			messagingTemplate.convertAndSend("/topic/screen", JSON.toJSONString(bin));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
