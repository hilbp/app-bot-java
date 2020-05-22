package com.hilbp.adb.action.type;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.hilbp.adb.action.base.ActionState;
import com.hilbp.adb.action.base.ActionType;
import com.hilbp.adb.entity.Action;
import com.hilbp.adb.util.StringUtil;
import com.hilbp.adb.util.UiAutoMatorUtil;

import se.vidstige.jadb.JadbDevice;

@Component
public class SearchTargetNode extends ActionType {
	
	@Autowired
	private ApplicationContext applicationContext;

	@Override
	public void operate(JadbDevice device, Action action) {
		this.run(device, action);
	}
	
	public void run(JadbDevice device, Action action) {
		
		this.beforExecuteShell(device, action);
		adbShellUtil.saveUiFile(device, action.getUiSavePath());
		this.afterExecuteShell(device, action);
		
		//保存状态
		String actionStateName = action.getActionStateName();
		if(StringUtil.isEmpty(actionStateName)) return;
		ActionState ActionState = (ActionState)applicationContext.getBean(actionStateName);
		ActionState.clear();
		ActionState.setState(action, UiAutoMatorUtil.getTargetNode(action), UiAutoMatorUtil.getTargetCoord(action));
	}
	
	
	
}
