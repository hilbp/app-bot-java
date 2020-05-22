package com.hilbp.adb.action.type.quote;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.hilbp.adb.action.base.QuoteActionType;
import com.hilbp.adb.entity.Action;
import com.hilbp.adb.entity.Coord;
import com.hilbp.adb.entity.Result;
import com.hilbp.adb.util.StaticValue;
import com.hilbp.adb.util.UiAutoMatorUtil;

import se.vidstige.jadb.JadbDevice;

/**
 * @desc 通过布局文件获取坐标，然后点击
 * @desc 适用条件：同一个页面多个node操作后页面不发生跳转；或一个node点击后发生跳转或者不跳转
 * @author hilbp
 */
@Component
public class QuoteClickTargetNode extends QuoteActionType {
	
	@Override
	public void operate(JadbDevice device, Action action, Result result) {
		result.setSuccessed(this.run(device, action));
	}
	
	public boolean run(JadbDevice device, Action action) {
		
		//获取ui文件
		boolean flag = true;
		if(action.getNotGetNewUi() == null || !action.getNotGetNewUi()) {
			if(this.beforExecuteShell(device, action)) {
				adbShellUtil.saveUiFile(device, action.getUiSavePath());
				if(this.afterExecuteShell(device, action)) {
					flag = true;
				} else flag = false;
			} else flag = false;
		}
		
		if(flag) {
			//通过xpath搜索坐标位置集合
			List<Coord> coords = UiAutoMatorUtil.getTargetCoord(action);
			//进行点击操作
			flag =this.click(device, action, coords);
		}
		
		return flag;
	}
	
	//点击操作
	public boolean click(JadbDevice device, Action action, List<Coord> coords) {
		
		Assert.isTrue(coords.size() > 0, StaticValue.MSG_COORD_EMPTY);
		boolean flag = false;
		for(Coord coord : coords) {
			try {
				if(!this.beforExecuteShell(device, action))
					continue;
				
				adbShellUtil.click(device, coord.getX(), coord.getY());
				Thread.sleep(1000);
				
				if(!this.beforExecuteShell(device, action))
					continue;
				
				flag = true;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return flag;
	}

}
