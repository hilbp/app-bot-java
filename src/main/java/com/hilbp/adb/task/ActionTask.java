package com.hilbp.adb.task;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hilbp.adb.action.ActionSchedule;
import com.hilbp.adb.entity.Action;
import com.hilbp.adb.yml.ActionDataBinder;

import se.vidstige.jadb.JadbDevice;

@Component
public class ActionTask implements Runnable {
	
	@Autowired
	private ActionDataBinder actionDataBinder;
	@Autowired
	private ActionSchedule actionSchedule;
	
	private String name;
	private JadbDevice device;
	
	public void setMeta(JadbDevice device, String name) {
		this.device = device;
		this.name = name;
	}

	@Override
	public void run() {
		this.task();
	}
	
	public void task() {
		List<Action> actions = actionDataBinder.actionBinderYml(name).getActions();
		actionSchedule.run(device, actions);
	}

}
