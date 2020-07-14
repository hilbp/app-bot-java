package com.hilbp.adb.action.type;

import org.springframework.stereotype.Component;

import com.hilbp.adb.action.type.base.ActionType;
import com.hilbp.adb.entity.Action;
import com.hilbp.adb.entity.ActionResult;

import se.vidstige.jadb.JadbDevice;

@Component
public class Keyevent extends ActionType {

	@Override
	public void operate(JadbDevice device, Action action) {
		this.run(device, action);
	}
	
	public void run(JadbDevice device, Action action) {
		
		typeExecuteUtil.beforExecuteShell(device, action);
//		adbShellUtil.exeShell(device, shell)
		typeExecuteUtil.afterExecuteShell(device, action);
			
	}

	@Override
	public void operate(JadbDevice device, Action action, ActionResult resutl) {
		// TODO Auto-generated method stub
		
	}
}
