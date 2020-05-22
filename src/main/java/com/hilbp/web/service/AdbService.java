package com.hilbp.web.service;

import java.util.List;

import se.vidstige.jadb.JadbDevice;

public interface AdbService {
	
	// 获取设备列表
	List<JadbDevice> getDevices();
	
	//微信自动聊天
	void wxAutoChat(JadbDevice device);
}
