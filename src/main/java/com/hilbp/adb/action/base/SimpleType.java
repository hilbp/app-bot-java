package com.hilbp.adb.action.base;

import com.hilbp.adb.entity.Action;

import se.vidstige.jadb.JadbDevice;

public interface SimpleType {
	
	//具体action的操作
	boolean operate(JadbDevice device, Action action);
	
	//action执行之前判断是否满足执行条件
	boolean beforExecuteShell(JadbDevice device, Action action);
	
	//action执行之后等待执行完成或超时
	boolean afterExecuteShell(JadbDevice device, Action action);
	
	
}
