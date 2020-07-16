package com.hilbp.adb.action.type;

import com.hilbp.adb.entity.Coord;
import com.hilbp.adb.entity.ManualControlEntity;
import com.hilbp.adb.state.SaveActionState;
import com.hilbp.adb.util.StaticValue;
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
public class ManualControl extends ActionType {

	@Autowired
	private SaveActionState saveActionState;
	
	@Override
	public void operate(JadbDevice device, Action action) {
		this.run(device, action);
	}
	
	public void run(JadbDevice device, Action action) {
		ManualControlEntity manualControlEntity = saveActionState.getStateData(action.getActionStateName());
		if(manualControlEntity.isOperated()) {
			switch(manualControlEntity.getType()) {
				case StaticValue.MANUAL_TYPE_CLIKE:
					Coord coord = manualControlEntity.getCoord();
					adbShellUtil.click(device, coord.getX(), coord.getY());
					break;
				case StaticValue.MANUAL_TYPE_INPUT:
					String msg = manualControlEntity.getText();
					adbShellUtil.sendMessage(device, msg, true);
					break;
			}
			manualControlEntity.setOperated(false);
		}
	}

	@Override
	public void operate(JadbDevice device, Action action, ActionResult resutl) {
		// TODO Auto-generated method stub
	}
}
