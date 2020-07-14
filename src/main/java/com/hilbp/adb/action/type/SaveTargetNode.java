package com.hilbp.adb.action.type;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hilbp.adb.action.type.base.ActionType;
import com.hilbp.adb.entity.Action;
import com.hilbp.adb.entity.ActionResult;
import com.hilbp.adb.state.SaveActionState;
import com.hilbp.adb.util.StaticValue;
import com.hilbp.adb.util.UiAutoMatorUtil;

import se.vidstige.jadb.JadbDevice;

/**
 * 从布局文件获取目标node并保存
 * @author hilbp
 *
 */
@Component
public class SaveTargetNode extends ActionType {
	
	@Autowired
	private SaveActionState saveActionState;

	@Override
	public void operate(JadbDevice device, Action action) {
		this.run(device, action);
	}
	
	public void run(JadbDevice device, Action action) {
		
		typeExecuteUtil.beforExecuteShell(device, action);
		adbShellUtil.saveUiFile(device, action.getUiSavePath());
		typeExecuteUtil.afterExecuteShell(device, action);
		
		//保存状态：新的
		saveActionState.saveState(action, StaticValue.FIELD_TYPE_NODES , UiAutoMatorUtil.getTargetNode(action));
		
	}

	@Override
	public void operate(JadbDevice device, Action action, ActionResult resutl) {
		// TODO Auto-generated method stub
		
	}
}
