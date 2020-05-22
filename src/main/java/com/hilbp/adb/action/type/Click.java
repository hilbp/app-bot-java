package com.hilbp.adb.action.type;

import org.springframework.stereotype.Component;

import com.hilbp.adb.action.base.ActionType;
import com.hilbp.adb.entity.Action;
import com.hilbp.adb.entity.Coord;

import lombok.extern.slf4j.Slf4j;
import se.vidstige.jadb.JadbDevice;

@Component
@Slf4j
public class Click extends ActionType {
	
	@Override
	public void operate(JadbDevice device, Action action) {
		this.run(device, action);
	}
	
	public void run(JadbDevice device, Action action) {
				
		this.beforExecuteShell(device, action);
		
		//如果父action给子action传递的坐标，优先执行子action的，二者选其一
		Coord coord = action.getLocation();
		if(coord == null) {
			coord = action.getParentTochildLocation();
		}
		if(coord == null)
			log.info("坐标为空，可能会有异常，是不是搜索的resource-id不合适？？");
		adbShellUtil.click(device, coord.getX(), coord.getY());
		
		this.afterExecuteShell(device, action);
			
	}

}
