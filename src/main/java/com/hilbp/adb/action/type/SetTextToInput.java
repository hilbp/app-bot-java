package com.hilbp.adb.action.type;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.hilbp.adb.action.type.base.ActionType;
import com.hilbp.adb.entity.Action;
import com.hilbp.adb.entity.Node;
import com.hilbp.adb.entity.ActionResult;
import com.hilbp.adb.state.ActionState;
import com.hilbp.adb.util.NlpUtil;
import com.hilbp.adb.util.StringUtil;

import lombok.extern.slf4j.Slf4j;
import se.vidstige.jadb.JadbDevice;

/**
 * @desc 有两个作用，将其设置到input中：
 * 1.向文本设置自定义用语 action-state-name = null
 * 2.从actionState获取源文本调用语义接口返回的文本 action-state-name != null
 * @author hilbp
 *
 */

@Component
@Slf4j
public class SetTextToInput extends ActionType {
	
	@Autowired
	ApplicationContext applicationContext;
	
	@Autowired
	private NlpUtil nlpUtil;
	
	@Override
	public void operate(JadbDevice device, Action action) {
		this.run(device, action);
	}
	
	public void run(JadbDevice device, Action action) {
				
		this.beforExecuteShell(device, action);
		String actionStateName = action.getActionStateName();
		
		String msg = null;
		if(StringUtil.isNotEmpty(actionStateName)) {
			msg = this.getChatMsg(actionStateName);
		}else {
			msg = "你好啊，聊聊天嘛";
		}
		adbShellUtil.sendMessage(device, msg, true);
		
		this.afterExecuteShell(device, action);
			
	}
	
	//语义识别
	public String getChatMsg(String actionStateName) {
		
		ActionState actionState = (ActionState) applicationContext.getBean(actionStateName);
		
		List<Node> nodes = actionState.getNodes();
		if(nodes.size() == 0) {
			return "你在嘤嘤嘤什么呀！";
		}
		
		String message = nodes.get(nodes.size() - 1).getText();
		log.info("→   识别内容：{}", message);
		
		String msg = nlpUtil.getChatMsg(message);
		log.info("→   聊天内容：{}", msg);
		
		return msg;
	}

	@Override
	public void operate(JadbDevice device, Action action, ActionResult resutl) {
		// TODO Auto-generated method stub
		
	}
	
}
