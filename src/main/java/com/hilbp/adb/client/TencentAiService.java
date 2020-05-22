package com.hilbp.adb.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "test", url = "https://api.ai.qq.com/fcgi-bin/nlp/nlp_textchat")
public interface TencentAiService {
	

	/**
	 * 腾讯智能聊天
	 */
	@RequestMapping(method = RequestMethod.GET, value = "")
	String tencentChatBot(
			@RequestParam("app_id") int appId,
			@RequestParam("time_stamp") long timeStamp,
			@RequestParam("nonce_str") String nonceStr,
			@RequestParam("sign") String sign,
			@RequestParam("session") String session,
			@RequestParam("question") String  question);
	

}
