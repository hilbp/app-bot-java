package com.hilbp.adb.action.type;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hilbp.adb.action.type.base.ActionType;
import com.hilbp.adb.entity.Action;
import com.hilbp.adb.entity.ActionResult;
import com.hilbp.adb.entity.Coord;
import com.hilbp.adb.state.SaveActionState;
import com.hilbp.adb.util.MatchTemplateUtil;
import com.hilbp.adb.util.StaticValue;

import se.vidstige.jadb.JadbDevice;

/**
 * 截屏，然后根据控件图标利用javacv匹配图标
 * 1.截屏的保存位置
 * 2.目标图的保存位置
 * 3.匹配结果的保存位置
 * 4.阈值设置
 * @author hilbp
 *
 */
@Component
public class SaveMatchNode extends ActionType {
	
	@Autowired
	private SaveActionState saveActionState;
	
	@Override
	public void operate(JadbDevice device, Action action, ActionResult resutl) {}
	
	@Override
	public void operate(JadbDevice device, Action action) {
		this.run(device, action);
	}
	
	public void run(JadbDevice device, Action action) {
		String sourcePath = action.getSourcePath();
		
		//先截屏
		typeExecuteUtil.beforExecuteShell(device, action);
		adbShellUtil.getScreenshot(device, sourcePath);
		typeExecuteUtil.afterExecuteShell(device, action);
		
		//保存状态
		String templatePath = action.getTemplatePath();
		List<Coord> coords = MatchTemplateUtil.match(sourcePath, templatePath);
		saveActionState.saveState(action, StaticValue.FIELD_TYPE_COORDS , coords);
	}

	
}
