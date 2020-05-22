package com.hilbp.adb.operate;

import com.hilbp.adb.entity.Action;

import se.vidstige.jadb.JadbDevice;

public abstract class CustomOperate {
	
	public abstract void run(JadbDevice device, Action action);
}
