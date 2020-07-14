package com.hilbp.adb.action.type2.base;

import com.hilbp.adb.entity.Action;

import se.vidstige.jadb.JadbDevice;

public interface IType {
	
	//设置action
	void setAction(Action action);
	//具体action的操作
	void operate(JadbDevice device);

}
