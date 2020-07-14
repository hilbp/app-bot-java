package com.hilbp.adb.action.type;

import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.hilbp.adb.action.type.base.ActionType;
import com.hilbp.adb.entity.Action;
import com.hilbp.adb.entity.ActionResult;
import com.hilbp.adb.entity.Coord;
import com.hilbp.adb.util.StaticValue;

import se.vidstige.jadb.JadbDevice;

@Component
//@Slf4j
public class Click extends ActionType {
	
	@Override
	public void operate(JadbDevice device, Action action) {
		this.run(device, action);
	}
	
	public void run(JadbDevice device, Action action) {
				
		typeExecuteUtil.beforExecuteShell(device, action);
		// 如果父action给子action传递的坐标，优先执行子action的，二者选其一
		Coord coord = action.getLocation();
		if(coord == null) {
			coord = action.getParentTochildLocation();
		}
		Assert.isTrue(coord != null, action.getName() + StaticValue.MSG_COORD_EMPTY);
		
		adbShellUtil.click(device, coord.getX(), coord.getY());
		typeExecuteUtil.afterExecuteShell(device, action);
			
	}

	@Override
	public void operate(JadbDevice device, Action action, ActionResult resutl) {
		// TODO Auto-generated method stub
		
	}

}
