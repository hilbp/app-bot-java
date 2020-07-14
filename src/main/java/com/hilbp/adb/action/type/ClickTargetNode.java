package com.hilbp.adb.action.type;

import java.util.List;

import org.springframework.stereotype.Component;

import com.hilbp.adb.action.type.base.ActionType;
import com.hilbp.adb.entity.Action;
import com.hilbp.adb.entity.ActionResult;
import com.hilbp.adb.entity.Coord;
import com.hilbp.adb.util.UiAutoMatorUtil;

import se.vidstige.jadb.JadbDevice;

/**
 * @desc 通过布局文件获取坐标，然后点击
 * @author hilbp
 */
@Component
public class ClickTargetNode extends ActionType {
	
	@Override
	public void operate(JadbDevice device, Action action) {
		this.run(device, action);
	}
	
	public void run(JadbDevice device, Action action) {
		
		//获取ui文件
		if(action.getIsNotGetNewUi() == null || !action.getIsNotGetNewUi()) {
			typeExecuteUtil.beforExecuteShell(device, action);
			adbShellUtil.saveUiFile(device, action.getUiSavePath());
			typeExecuteUtil.afterExecuteShell(device, action);
		}
		
		//通过xpath搜索坐标位置集合
		List<Coord> coords = UiAutoMatorUtil.getTargetCoord(action);
		//进行点击操作
		typeExecuteUtil.click(device, action, coords);
	}
	
	
	@Override
	public void operate(JadbDevice device, Action action, ActionResult resutl) {}

}
