package com.hilbp.web.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hilbp.adb.action.ActionSchedule;
import com.hilbp.adb.entity.Action;
import com.hilbp.adb.util.AdbShellUtil;
import com.hilbp.adb.yml.ActionDataBinder;

import se.vidstige.jadb.JadbDevice;
import se.vidstige.jadb.JadbException;

@RestController
@RequestMapping("/test")
public class TestController {
	
	@Autowired
	AdbShellUtil adbShellUtil;
	
	@Autowired
	ActionDataBinder actionDataBinder;
	
	@Autowired
	ActionSchedule actionSchedule;
	
	@RequestMapping(value = "/soul/like", method = RequestMethod.GET)
	public ResponseEntity<Object> test(String service_name) throws IOException, JadbException{
		
		JadbDevice device = adbShellUtil.getDevices().get(0);
		List<Action> actions = actionDataBinder.actionBinderYml("soul-do-like").getActions();
		actionSchedule.run(device, actions);

		return new ResponseEntity<Object>(1, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/doyin/like", method = RequestMethod.GET)
	public ResponseEntity<Object> test2(String service_name) throws IOException, JadbException{
		
		JadbDevice device = adbShellUtil.getDevices().get(0);
		List<Action> actions = actionDataBinder.actionBinderYml("doyin-comment-dolike").getActions();
		actionSchedule.run(device, actions);

		return new ResponseEntity<Object>(1, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/t", method = RequestMethod.GET)
	public ResponseEntity<Object> test4(HttpServletResponse response) throws IOException, JadbException{
		
		int i = 1;
		OutputStream out = response.getOutputStream();
		while(i < 10) {
			
			out.write(i++);
		}
		out.close();
		return new ResponseEntity<Object>(1, HttpStatus.OK);
	}
	
	
}
