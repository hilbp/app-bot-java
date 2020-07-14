package com.hilbp.adb.action.type;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.hilbp.adb.action.type.base.ActionType;
import com.hilbp.adb.entity.Action;
import com.hilbp.adb.entity.ActionResult;
import com.hilbp.adb.state.AbstractActionState;

import se.vidstige.jadb.JadbDevice;

/**
 * 手动操作屏幕
 * 1.支持手动点击
 * 2.支持发送文字
 * @author hilbp
 *
 */
@Component
public class ManualOperate extends ActionType {

	@Autowired
	ApplicationContext applicationContext;
	
	@Override
	public void operate(JadbDevice device, Action action) {
		this.run(device, action);
	}
	
	public void run(JadbDevice device, Action action) {
		
		/**
		 * 1.读取状态
		 * 2.操作
		 */
		AbstractActionState<?> actionState = (AbstractActionState<?>) applicationContext.getBean(action.getActionStateName());
		
	}

	@Override
	public void operate(JadbDevice device, Action action, ActionResult resutl) {
		// TODO Auto-generated method stub
		
	}
}
