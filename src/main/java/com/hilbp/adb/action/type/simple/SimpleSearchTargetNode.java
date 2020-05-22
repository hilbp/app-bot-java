package com.hilbp.adb.action.type.simple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.hilbp.adb.action.base.ActionState;
import com.hilbp.adb.action.base.SimpleActionType;
import com.hilbp.adb.entity.Action;
import com.hilbp.adb.util.StringUtil;
import com.hilbp.adb.util.UiAutoMatorUtil;

import se.vidstige.jadb.JadbDevice;

@Component
public class SimpleSearchTargetNode extends SimpleActionType {
	
	@Autowired
	private ApplicationContext applicationContext;

	@Override
	public boolean operate(JadbDevice device, Action action) {
		return this.run(device, action);
	}
	
	public boolean run(JadbDevice device, Action action) {
		
		boolean flag = false;
		if(this.beforExecuteShell(device, action)) {
			adbShellUtil.saveUiFile(device, action.getUiSavePath());
			if(this.afterExecuteShell(device, action)) {
				flag = true;
				//保存状态
				String actionStateName = action.getActionStateName();
				if(!StringUtil.isEmpty(actionStateName)) {
					ActionState ActionState = (ActionState)applicationContext.getBean(actionStateName);
					ActionState.clear();
					ActionState.setState(action,
							UiAutoMatorUtil.getTargetNode(action),
							UiAutoMatorUtil.getTargetCoord(action));
				}	
			}
		}
		return flag;
	}
	
	
	
}
