package com.hilbp.adb.action.type;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.hilbp.adb.action.type.base.ActionType;
import com.hilbp.adb.entity.Action;
import com.hilbp.adb.entity.ActionResult;
import com.hilbp.adb.operate.CustomOperate;

import se.vidstige.jadb.JadbDevice;

@Component
//@Slf4j
public class Custom extends ActionType {
	
	@Autowired
	ApplicationContext applicationContext;

	@Override
	public void operate(JadbDevice device, Action action) {
		
		CustomOperate obj = (CustomOperate) applicationContext.getBean(action.getCustomBeanName());
		obj.run(device, action);
		
	}

	@Override
	public void operate(JadbDevice device, Action action, ActionResult resutl) {
		// TODO Auto-generated method stub
		
	}

}
