package com.hilbp.adb.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.hilbp.adb.client.SimpleBotClientService;
import com.hilbp.adb.entity.ChatMsgEntity;

@Component
public class ChatApiUtil {
	
	@Autowired
	private SimpleBotClientService simpleBotClientService;
	
	public String getChatMsg(String msg) {
		
		String jsonStr = simpleBotClientService.chatBot("free", 0, msg);
		ChatMsgEntity chatMsgEntity = JSON.parseObject(jsonStr, ChatMsgEntity.class);
		return chatMsgEntity.getContent();
	}
}
