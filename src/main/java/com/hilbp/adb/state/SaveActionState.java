package com.hilbp.adb.state;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

import com.hilbp.adb.entity.Action;
import com.hilbp.adb.entity.ActionState;
import com.hilbp.adb.util.StringUtil;

/**
 * 支持保存：
 * 1.布局文件的node集合
 * 2.得到的坐标集合
 * 3.文本
 * @author hilbp
 *
 */
@Component
public class SaveActionState {
	
	protected Map<String, ActionState<Object>> state = new ConcurrentHashMap<String, ActionState<Object>>();
	
	@SuppressWarnings("unchecked")
	public <T> T getStateData(String key) {
		try {
			return (T) state.get(key).getData();
		} catch(RuntimeException e) {
			return null;
		}
	}

	public void saveState(Action action, String type, Object data) {
		
		String actionStateName = action.getActionStateName();
		if(StringUtil.isEmpty(actionStateName)) return;
		ActionState<Object> actionState = new ActionState<Object>();
		actionState.setAction(action);
		actionState.setType(type);
		actionState.setData(data);
		state.put(actionStateName, actionState);
	}
	
	public void clearState(String key) {
		state.put(key, null);
	}
	
	

}
