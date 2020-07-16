package com.hilbp.adb.action.type2;

import com.hilbp.adb.action.type2.base.AbstractActionType;
import com.hilbp.adb.entity.Action;
import com.hilbp.adb.entity.ActionResult;

import org.springframework.util.Assert;
import se.vidstige.jadb.JadbDevice;

public class Back extends AbstractActionType {

	@Override
	public void setAction(Action action) {

	}

	@Override
	public void operate(JadbDevice device) {

	}

	@Override
	public void operate(JadbDevice device, ActionResult resutl) {
		// TODO Auto-generated method stub

	}

	public void run(JadbDevice device, Action action) {

		typeExecuteUtil.beforExecuteShell(device, action);
		int i = 0;
		while(i < 5) {
			adbShellUtil.back(device);
			if(adbShellUtil.isTargetActivity(device, action.getTargetActivity())) {
				return;
			}
			i++;
		}
		Assert.isTrue(i >= 5, "返回失败");
	}

}
