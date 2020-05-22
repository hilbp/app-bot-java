package com.hilbp.adb.action.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.hilbp.adb.entity.Action;
import com.hilbp.adb.util.AdbShellUtil;
import com.hilbp.adb.util.StringUtil;

import lombok.extern.slf4j.Slf4j;
import se.vidstige.jadb.JadbDevice;

@Slf4j
public abstract class ActionType implements Type {
	
	@Autowired
	protected AdbShellUtil adbShellUtil;
	
	public abstract void operate(JadbDevice device, Action action);
	
	//命令之前判断是否满足执行条件
	public void beforExecuteShell(JadbDevice device, Action action) {
		
		log.info("→   判断命令执行的前置条件");
		if(StringUtil.isEmpty(action.getCurrentActivity()))
			return;
		
		Assert.isTrue(adbShellUtil.isTargetActivity(device, action.getCurrentActivity()),
				String.format("不满足执行条件：%s", action.getName()));
	}
	
	//发送命令后等待执行完成或超时
	public void afterExecuteShell(JadbDevice device, Action action) {
		
		log.info("→   等待命令执行完成");
		
		if(StringUtil.isEmpty(action.getTargetActivity())) 
			return;
		
		int i = 0;
		while(i < 15) {
			if(adbShellUtil.isTargetActivity(device, action.getTargetActivity()))
				return;
			try {
				Thread.sleep(1000);
				i++;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		Assert.isTrue(i < 5, String.format("命令执行失败:：%s", action.getName()));
	}
	
}
