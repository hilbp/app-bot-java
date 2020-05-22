package com.hilbp.adb.action.type.quote;

import org.springframework.stereotype.Component;

import com.hilbp.adb.action.base.QuoteActionType;
import com.hilbp.adb.entity.Action;
import com.hilbp.adb.entity.Result;

import se.vidstige.jadb.JadbDevice;

@Component
public class QuoteOpen extends QuoteActionType {
	
	@Override
	public void operate(JadbDevice device, Action action, Result result) {
		
		result.setSuccessed(this.run(device, action));
	}
	
	public boolean run(JadbDevice device, Action action) {
		
		if(adbShellUtil.isTargetActivity(device, action.getTargetActivity()))
			return true;
		
		adbShellUtil.openActivity(device, action.getTargetActivity());
		return this.afterExecuteShell(device, action);
		
	}
	
}
