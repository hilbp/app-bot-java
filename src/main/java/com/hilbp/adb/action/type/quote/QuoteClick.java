package com.hilbp.adb.action.type.quote;

import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.hilbp.adb.action.base.QuoteActionType;
import com.hilbp.adb.entity.Action;
import com.hilbp.adb.entity.Coord;
import com.hilbp.adb.entity.Result;
import com.hilbp.adb.util.StaticValue;

import se.vidstige.jadb.JadbDevice;

@Component
//@Slf4j
public class QuoteClick extends QuoteActionType {
	
	@Override
	public void operate(JadbDevice device, Action action, Result result) {
		
		result.setSuccessed(this.run(device, action));
	}
	
	public boolean run(JadbDevice device, Action action) {
	
		boolean flag = false;
		if(this.beforExecuteShell(device, action)) {
			
			//如果父action给子action传递的坐标，优先执行子action的，二者选其一
			Coord coord = action.getLocation();
			if(coord == null) 
				coord = action.getParentTochildLocation();
			
			Assert.isTrue(coord != null, action.getName() + StaticValue.MSG_COORD_EMPTY);
			
			adbShellUtil.click(device, coord.getX(), coord.getY());
			flag = this.afterExecuteShell(device, action);
		}
		return flag;
	}

}
