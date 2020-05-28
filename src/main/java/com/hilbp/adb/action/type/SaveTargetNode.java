package com.hilbp.adb.action.type;

import org.springframework.stereotype.Component;

import com.hilbp.adb.action.type.base.ActionType;
import com.hilbp.adb.entity.Action;
import com.hilbp.adb.entity.ActionResult;

import se.vidstige.jadb.JadbDevice;

@Component
public class SaveTargetNode extends ActionType {
	
	

	@Override
	public void operate(JadbDevice device, Action action) {
		this.run(device, action);
	}
	
	public void run(JadbDevice device, Action action) {
		
		this.beforExecuteShell(device, action);
		adbShellUtil.saveUiFile(device, action.getUiSavePath());
		this.afterExecuteShell(device, action);
		
		//保存状态
		this.saveActionState(action);
		
		
	}

	@Override
	public void operate(JadbDevice device, Action action, ActionResult resutl) {
		// TODO Auto-generated method stub
		
	}
}
