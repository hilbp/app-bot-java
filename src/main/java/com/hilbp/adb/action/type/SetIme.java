package com.hilbp.adb.action.type;

import org.springframework.stereotype.Component;

import com.hilbp.adb.action.base.ActionType;
import com.hilbp.adb.entity.Action;

import se.vidstige.jadb.JadbDevice;

@Component
//@Slf4j
public class SetIme extends ActionType {
	
	@Override
	public void operate(JadbDevice device, Action action) {
		this.run(device, action);
	}
	
	public void run(JadbDevice device, Action action) {
				
		this.beforExecuteShell(device, action);
		adbShellUtil.setIme(device, action.getImeName());
		this.afterExecuteShell(device, action);
			
	}

}
