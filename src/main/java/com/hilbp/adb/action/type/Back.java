package com.hilbp.adb.action.type;

import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.hilbp.adb.action.type.base.ActionType;
import com.hilbp.adb.entity.Action;
import com.hilbp.adb.entity.ActionResult;

import se.vidstige.jadb.JadbDevice;

@Component
public class Back extends ActionType {

	@Override
	public void operate(JadbDevice device, Action action) {
		this.run(device, action);
	}
	
	public void run(JadbDevice device, Action action) {
		
		this.beforExecuteShell(device, action);
		int i = 0;
		while(i < 5) {
			adbShellUtil.back(device);
			if(adbShellUtil.isTargetActivity(device, action.getTargetActivity())) {
				return;
			}
			i++;
		}
		Assert.isTrue(i >= 5, "返回失败");
	}

	@Override
	public void operate(JadbDevice device, Action action, ActionResult resutl) {
		// TODO Auto-generated method stub
		
	}
}
