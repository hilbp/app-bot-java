package com.hilbp.adb.action.type.simple;

import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.hilbp.adb.action.base.SimpleActionType;
import com.hilbp.adb.entity.Action;
import com.hilbp.adb.entity.Coord;

import se.vidstige.jadb.JadbDevice;

@Component
//@Slf4j
public class SimpleClick extends SimpleActionType {
	
	@Override
	public boolean operate(JadbDevice device, Action action) {
		return this.run(device, action);
	}
	
	public boolean run(JadbDevice device, Action action) {
	
		boolean flag = false;
		if(this.beforExecuteShell(device, action)) {
			
			//如果父action给子action传递的坐标，优先执行子action的，二者选其一
			Coord coord = action.getLocation();
			if(coord == null) 
				coord = action.getParentTochildLocation();
			Assert.isTrue(coord != null, "坐标为空，可能会有异常，是不是搜索的resource-id不合适？？");
			
			adbShellUtil.click(device, coord.getX(), coord.getY());
			flag = this.afterExecuteShell(device, action);
		}
		return flag;
	}

}
