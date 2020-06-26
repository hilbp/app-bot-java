package com.hilbp.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

import com.hilbp.adb.action.ActionSchedule;
import com.hilbp.adb.entity.SocketInput;
import com.hilbp.adb.entity.SocketOutput;
import com.hilbp.adb.task.ActionTaskSchedule;
import com.hilbp.adb.util.AdbShellUtil;
import com.hilbp.adb.yml.ActionDataBinder;

import se.vidstige.jadb.JadbDevice;

@Controller
//@Slf4j
public class ScoketServerController {
	
	@Autowired
	AdbShellUtil adbShellUtil;
	
	@Autowired
	ActionDataBinder actionDataBinder;
	
	@Autowired
	ActionSchedule actionSchedule;
	
	@Autowired
	ActionTaskSchedule actionTaskSchedule;
	

	/**
	 * 初始化
	 * 1.获取菜单
	 */
	@MessageMapping("/init")
	@SendTo("/topic/greetings")
	public SocketOutput init(SocketInput input) throws Exception {

		return new SocketOutput("Hello, " + HtmlUtils.htmlEscape(input.getName()) + "!");
	}

	/**
	 * 各种操作
	 */
	@MessageMapping("/start")
	public void start(SocketInput input) throws Exception {

		JadbDevice device = adbShellUtil.getDevices().get(0);	
		actionTaskSchedule.start(device, input.getName());

	}
	
	/**
	 * 停止线程
	 */
	@MessageMapping("/stop")
	public void stopThread(SocketInput input) throws Exception {
		actionTaskSchedule.stop();
	}
	
	

}
