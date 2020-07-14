package com.hilbp.adb.state;

import java.util.HashMap;
import java.util.Map;

import com.hilbp.adb.entity.Action;

/**
 * 支持保存：
 * 1.布局文件的node集合
 * 2.得到的坐标集合
 * 3.文本
 * @author hilbp
 *
 */
public abstract class AbstractActionState<T> {
	
	protected Action action;
	protected String type;
	protected T data;
	protected Map<String, Object> cache = new HashMap<String, Object>();
	
	public Action getAction() {
		
		return this.action;
	}
	
	public void setAction(Action action) {
		
		this.action = action;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public void setState(Action action, String type, T data) {
		this.action = action;
		this.type = type;
		this.data = data;
	}
	
	public void clear() {
		this.action = null;
		this.type = null;
		this.data = null;
	}
	
	

}
