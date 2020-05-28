package com.hilbp.adb.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hilbp.adb.client.SimpleBotClientService;

@Component
public class ChatApiUtil {
	
	@Autowired
	private SimpleBotClientService simpleBotClientService;
	
	public String getChatMsg(String msg) {
		
		String jsonStr = simpleBotClientService.chatBot("free", 0, msg);
		JSONObject jsonObj = JSON.parseObject(jsonStr);
		String content = jsonObj.getString("content");
		return content;
	}
}
