package com.hilbp.adb.action.type.simple;

import org.springframework.stereotype.Component;

import com.hilbp.adb.action.base.SimpleActionType;
import com.hilbp.adb.entity.Action;

import se.vidstige.jadb.JadbDevice;

@Component
public class SimpleBack extends SimpleActionType {

	@Override
	public boolean operate(JadbDevice device, Action action) {
		return this.run(device, action);
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
