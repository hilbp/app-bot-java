package com.hilbp.adb.action.type.base;

import com.hilbp.adb.entity.Action;

import se.vidstige.jadb.JadbDevice;

public interface Type {
	
	//具体action的操作
	void operate(JadbDevice device, Action action);
	
	//action执行之前判断是否满足执行条件
	void beforExecuteShell(JadbDevice device, Action action);
	
	//action执行之后等待执行完成或超时
	void afterExecuteShell(JadbDevice device, Action action);
	
	
}
