package com.hilbp.adb.action.type;

import org.springframework.stereotype.Component;

import com.hilbp.adb.action.base.ActionType;
import com.hilbp.adb.entity.Action;

import se.vidstige.jadb.JadbDevice;

@Component
public class Keyevent extends ActionType {

	@Override
	public void operate(JadbDevice device, Action action) {
		this.run(device, action);
	}
	
	public void run(JadbDevice device, Action action) {
		
		this.beforExecuteShell(device, action);
//		adbShellUtil.exeShell(device, shell)
		this.afterExecuteShell(device, action);
			
	}
}
