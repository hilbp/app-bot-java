package com.hilbp.adb.action.base;

import org.springframework.beans.factory.annotation.Autowired;

import com.hilbp.adb.entity.Action;
import com.hilbp.adb.entity.Result;
import com.hilbp.adb.util.AdbShellUtil;
import com.hilbp.adb.util.StringUtil;

import lombok.extern.slf4j.Slf4j;
import se.vidstige.jadb.JadbDevice;

@Slf4j
public abstract class QuoteActionType implements QuoteType {
	
	@Autowired
	protected AdbShellUtil adbShellUtil;
	
	public abstract void operate(JadbDevice device, Action action, Result result);
	
	//命令之前判断是否满足执行条件
	public boolean beforExecuteShell(JadbDevice device, Action action) {
		
		log.info("→   判断命令执行的前置条件");
		boolean flg = false;
		if(StringUtil.isEmpty(action.getCurrentActivity())
				|| adbShellUtil.isTargetActivity(device, action.getCurrentActivity()))
			flg = true;
		return flg;
	}
	
	//发送命令后等待执行完成或超时
	public boolean afterExecuteShell(JadbDevice device, Action action) {
		
		log.info("→   等待命令执行完成");
		boolean flg = false;
		int i = 0;
		while(i < 5) {
			try {
				if(StringUtil.isEmpty(action.getTargetActivity())
						|| adbShellUtil.isTargetActivity(device, action.getTargetActivity())) {
					flg = true;
					break;
				}
				Thread.sleep(3000);
				i++;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		return flg;
	}
	
	
	
	
	
	

	
}
