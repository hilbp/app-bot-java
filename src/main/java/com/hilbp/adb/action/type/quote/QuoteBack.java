package com.hilbp.adb.action.type.quote;

import org.springframework.stereotype.Component;

import com.hilbp.adb.action.base.QuoteActionType;
import com.hilbp.adb.entity.Action;
import com.hilbp.adb.entity.Result;

import se.vidstige.jadb.JadbDevice;

@Component
public class QuoteBack extends QuoteActionType {

	@Override
	public void operate(JadbDevice device, Action action, Result result) {
		result.setSuccessed(this.run(device, action));
	}
	
	public boolean run(JadbDevice device, Action action) {
		
		boolean flag = false;
		if(this.beforExecuteShell(device, action)) {
			int i = 0;
			while(i < 5) {
				if(adbShellUtil.isTargetActivity(device, action.getTargetActivity())) {
					flag = true;
					break;
				}
				adbShellUtil.back(device);
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				i++;
			}
		}
		return flag;
	}
}
