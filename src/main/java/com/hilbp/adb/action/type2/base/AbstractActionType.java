package com.hilbp.adb.action.type2.base;

import org.springframework.beans.factory.annotation.Autowired;

import com.hilbp.adb.entity.Action;
import com.hilbp.adb.entity.ActionResult;
import com.hilbp.adb.util.AdbShellUtil;
import com.hilbp.adb.util.TypeExecuteUtil;

import se.vidstige.jadb.JadbDevice;

//@Slf4j
public abstract class AbstractActionType implements IType {
	
	protected ThreadLocal<Object> target = new ThreadLocal<Object>();

	@Autowired
	protected AdbShellUtil adbShellUtil;
	
	@Autowired
	protected TypeExecuteUtil typeExecuteUtil;
	
	public abstract void setAction(Action action);
	public abstract void operate(JadbDevice device);
	public abstract void operate(JadbDevice device, ActionResult result);
	
	
	
	

	
}
