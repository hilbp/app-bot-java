package com.hilbp.adb.action.type.simple;

import java.util.List;

import org.springframework.stereotype.Component;

import com.hilbp.adb.action.base.SimpleActionType;
import com.hilbp.adb.entity.Action;
import com.hilbp.adb.entity.Coord;
import com.hilbp.adb.util.UiAutoMatorUtil;

import se.vidstige.jadb.JadbDevice;

/**
 * @desc 通过布局文件获取坐标，然后点击
 * @author hilbp
 */
@Component
public class SimpleClickTargetNode extends SimpleActionType {
	
	@Override
	public boolean operate(JadbDevice device, Action action) {
		return this.run(device, action);
	}
	
	public boolean run(JadbDevice device, Action action) {
		
		//获取ui文件
		if(action.getNotGetNewUi() == null || !action.getNotGetNewUi()) {
			if(!this.beforExecuteShell(device, action))
				return false;
			adbShellUtil.saveUiFile(device, action.getUiSavePath());
			if(!this.afterExecuteShell(device, action))
				return false;
		}

		//通过xpath搜索坐标位置集合
		List<Coord> coords = UiAutoMatorUtil.getTargetCoord(action);
		//进行点击操作
		return this.click(device, action, coords);
	}
	
	//点击操作
	public boolean click(JadbDevice device, Action action, List<Coord> coords) {
	
//		Assert.isTrue(coords == null || coords.isEmpty(), "居然没搜索到目标node，是不是xpath不对？？");
		
		for(Coord coord : coords) {
			try {
				 
				if(!this.beforExecuteShell(device, action))
					return false;
				
				adbShellUtil.click(device, coord.getX(), coord.getY());
				Thread.sleep(1000);
				if(!this.afterExecuteShell(device, action))
					return false;
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return true;
	}

}
