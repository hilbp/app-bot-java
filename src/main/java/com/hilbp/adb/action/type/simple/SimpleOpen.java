package com.hilbp.adb.action.type.simple;

import org.springframework.stereotype.Component;

import com.hilbp.adb.action.base.SimpleActionType;
import com.hilbp.adb.entity.Action;

import se.vidstige.jadb.JadbDevice;

@Component
public class SimpleOpen extends SimpleActionType {
	
	@Override
	public boolean operate(JadbDevice device, Action action) {
		
		return this.run(device, action);
		
	}
	
	public boolean run(JadbDevice device, Action action) {
		
		if(adbShellUtil.isTargetActivity(device, action.getTargetActivity()))
			return true;
		
		adbShellUtil.openActivity(device, action.getTargetActivity());
		return this.afterExecuteShell(device, action);
		
	}
	
}
