package com.hilbp.adb.action.type.base;

import com.hilbp.adb.entity.Action;

import se.vidstige.jadb.JadbDevice;

public interface Type {
	
	//具体action的操作
	void operate(JadbDevice device, Action action);

}
