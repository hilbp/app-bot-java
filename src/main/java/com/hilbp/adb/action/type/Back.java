package com.hilbp.adb.action.type;

import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.hilbp.adb.action.base.ActionType;
import com.hilbp.adb.entity.Action;

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
			if(adbShellUtil.isTargetActivity(device, action.getTargetActivity()))
				return;
			
			adbShellUtil.back(device);
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			i++;
		}
		
		Assert.isTrue(i >= 5, "返回失败");
	}
}
