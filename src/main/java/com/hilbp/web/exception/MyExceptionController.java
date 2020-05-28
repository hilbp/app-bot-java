package com.hilbp.web.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.hilbp.adb.action.ActionSchedule;
import com.hilbp.adb.util.AdbShellUtil;

@ControllerAdvice
//@Slf4j
public class MyExceptionController {
	
	@Autowired
	AdbShellUtil adbShellUtil;
	
	@Autowired
	@Qualifier("reflectActionSchedule")
	ActionSchedule actionSchedule;
	
	@ExceptionHandler(value = IllegalArgumentException.class)
	public void test(IllegalArgumentException e) {
		
//		Map<String, Object> res = new HashMap<String, Object>();
//		res.put("error", 1);
//		res.put("msg", e.getMessage());
//		res.put("e.getCause()", e.getCause());
//		res.put("e.getLocalizedMessage()", e.getLocalizedMessage());
//		res.put("e.getClass()", e.getClass());
//		List<Action> actions = soulDoLikeAction.getActions();
//		List<JadbDevice> devices = adbShellUtil.getDevices();
//		actionSchedule.run(devices.get(0), actions);
		
//		log.info("出现异常：{}", e.getMessage());
//		System.out.println(e.getMessage());
		
	}


}
