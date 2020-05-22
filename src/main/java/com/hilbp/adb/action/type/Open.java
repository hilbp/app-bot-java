package com.hilbp.adb.action.type;

import org.springframework.stereotype.Component;

import com.hilbp.adb.action.base.ActionType;
import com.hilbp.adb.entity.Action;

import se.vidstige.jadb.JadbDevice;

@Component
public class Open extends ActionType {
	
	@Override
	public void operate(JadbDevice device, Action action) {
		this.run(device, action);
	}
	
	public void run(JadbDevice device, Action action) {
	
		if(!adbShellUtil.isTargetActivity(device, action.getTargetActivity())) {
			adbShellUtil.openActivity(device, action.getTargetActivity());
			this.afterExecuteShell(device, action);
		}
	}
	
}
