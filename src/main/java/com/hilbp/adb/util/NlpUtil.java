package com.hilbp.adb.util;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import cn.xsshome.taip.nlp.TAipNlp;

/**
 * @desc 自然语言相关
 * @author hilbp
 *
 */
@Component
public class NlpUtil {
	
	@Autowired
	private ChatApiUtil chatApiUtil;
	
	@Autowired
	private TAipNlp tAipNlp;
	
	public String getChatMsg(String question) {
		
		try {
			String content;
			
			if(question.length() > 280) 
				question = question.substring(0, 280);
						
			String session = new Date().getTime() / 1000 + "";
			String result = tAipNlp.nlpTextchat(session, question);
			JSONObject jsonObject = JSON.parseObject(result);
			
			int ret = jsonObject.getIntValue("ret");
			if(ret == 0) {
				
				JSONObject data = jsonObject.getJSONObject("data");
				content = data.getString("answer");
			} else
				content = chatApiUtil.getChatMsg(question);
			
			return content;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return StaticValue.MSG_TEXT_EMPTY;
	}
}
