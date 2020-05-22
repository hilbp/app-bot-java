package com.hilbp.adb.action.type.quote;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.hilbp.adb.action.base.ActionState;
import com.hilbp.adb.action.base.QuoteActionType;
import com.hilbp.adb.entity.Action;
import com.hilbp.adb.entity.Result;
import com.hilbp.adb.util.StringUtil;
import com.hilbp.adb.util.UiAutoMatorUtil;

import se.vidstige.jadb.JadbDevice;

@Component
public class QuoteSearchTargetNode extends QuoteActionType {
	
	@Autowired
	private ApplicationContext applicationContext;

	@Override
	public void operate(JadbDevice device, Action action, Result result) {
		
		result.setSuccessed(this.run(device, action));
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
	
	//action执行结果的状态保存
	public void saveActionState(Action action) {
		
		String actionStateName = action.getActionStateName();
		if(!StringUtil.isEmpty(actionStateName)) {
			ActionState ActionState = (ActionState) applicationContext.getBean(actionStateName);
			ActionState.clear();
			ActionState.setState(action,
					UiAutoMatorUtil.getTargetNode(action),
					UiAutoMatorUtil.getTargetCoord(action));
		}	
	}
	
}
