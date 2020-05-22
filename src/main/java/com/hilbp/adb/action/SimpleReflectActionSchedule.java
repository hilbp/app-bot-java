package com.hilbp.adb.action;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.hilbp.adb.action.base.ActionState;
import com.hilbp.adb.action.base.SimpleActionType;
import com.hilbp.adb.entity.Action;
import com.hilbp.adb.entity.Coord;
import com.hilbp.adb.util.StaticValue;
import com.hilbp.adb.util.StringUtil;

import lombok.extern.slf4j.Slf4j;
import se.vidstige.jadb.JadbDevice;

@Slf4j
@Component
public class SimpleReflectActionSchedule implements ActionSchedule {
	
	@Autowired
	private ApplicationContext applicationContext;	
	
	@Override
	public void run(JadbDevice device, List<Action> actions) {
		
		this.runInitAction(device, actions);
		this.runRunningAction(device, actions);
	}
	
	//执行status=init状态的action（只执行一次）
	public void runInitAction(JadbDevice device, List<Action> actions) {
		System.out.println(3234);
		List<Action> initActions = this.filterAction(actions, "init");
		if(initActions == null || initActions.isEmpty()) 
			return;
		
		boolean flag = false;
		outloop: while(true) {
			if(flag == true) return;
			for(Action action : initActions) {
				if(!this.invoke(device, action))
					continue outloop;
			}
			flag = true;
		}
	}
	
	//执行status=running状态的action(循环执行)
	public void runRunningAction(JadbDevice device, List<Action> actions) {
		
		List<Action> runningActions = this.filterAction(actions, "running");
		if(runningActions == null || runningActions.isEmpty()) 
			return;
		
		while(true) {
			for(Action action : runningActions) {
				
				//执行父action
				if(!this.invoke(device, action)) break;
				//执行子action
				this.runChildAction(device, action, action.getChildActions());
			}
		}
	}
	
	//执行子action
	public void runChildAction(JadbDevice device, Action action, List<Action> childActions) {
		if(childActions == null) return;
		String actionStateName = action.getActionStateName();
		if(StringUtil.isEmpty(actionStateName)) return;
		ActionState actionState = (ActionState) applicationContext.getBean(actionStateName);

		if(action.getType().equals(StaticValue.TYPE_SEARCH_TARGET_NODE_SIMPLE)) {
			log.info("执行子步骤");
			List<Coord> coords = actionState.getCoords();
			for(Coord coord : coords) {
				for(Action childAction : childActions) {
					childAction.setParentTochildLocation(coord);
					if(!this.invoke(device, childAction)) break;
				}
			}
		}
		
	}
	
	//执行操作
	public boolean invoke(JadbDevice device, Action action) {
		log.info("current action: {}", action.getName());
		SimpleActionType type = (SimpleActionType) applicationContext.getBean(action.getType());
		boolean flag = type.operate(device, action);
		log.info("{} 执行结果： {}", action.getName(), flag);
		return flag;
	}
		
	//过滤action
	public List<Action> filterAction(List<Action> actions, String condition) {
		
		List<Action> temp = new ArrayList<Action>();
		for(Action action : actions) {
			if(!condition.equals(action.getStatus())) 
				continue;
			temp.add(action);
		}
		
		return temp;
	}
	
}
