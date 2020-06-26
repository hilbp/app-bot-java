package com.hilbp.adb.action.type;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.hilbp.adb.action.type.base.ActionType;
import com.hilbp.adb.entity.Action;
import com.hilbp.adb.entity.ActionResult;
import com.hilbp.adb.util.StaticValue;

import se.vidstige.jadb.JadbDevice;

/**
 * 投屏
 * @author hilbp
 *
 */
@Component
//@Slf4j
public class ScreenMirror extends ActionType {
	
	@Autowired
    private SimpMessagingTemplate messagingTemplate;
	
	@Override
	public void operate(JadbDevice device, Action action) {
		this.run(device, action);
	}
	
	public void run(JadbDevice device, Action action) {	
		byte[] bin = adbShellUtil.getScreenshot(device);
		messagingTemplate.convertAndSend(StaticValue.SOCKET_SCREEN_MIRROR, JSON.toJSONString(bin));
	}

	@Override
	public void operate(JadbDevice device, Action action, ActionResult resutl) {
		// TODO Auto-generated method stub
		
	}

}
