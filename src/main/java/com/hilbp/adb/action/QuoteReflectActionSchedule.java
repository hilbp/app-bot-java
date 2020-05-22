package com.hilbp.adb.action;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.hilbp.adb.action.base.ActionState;
import com.hilbp.adb.action.base.QuoteActionType;
import com.hilbp.adb.entity.Action;
import com.hilbp.adb.entity.Coord;
import com.hilbp.adb.entity.Result;
import com.hilbp.adb.util.StaticValue;
import com.hilbp.adb.util.StringUtil;

import lombok.extern.slf4j.Slf4j;
import se.vidstige.jadb.JadbDevice;

@Slf4j
@Component
public class QuoteReflectActionSchedule implements ActionSchedule {
	
	@Autowired
	private ApplicationContext applicationContext;	
	
	@Override
	public void run(JadbDevice device, List<Action> actions) {
		
		this.runInitAction(device, actions);
		this.runRunningAction(device, actions);
	}
	
	//执行status=init状态的action（只执行一次）
	public void runInitAction(JadbDevice device, List<Action> actions) {
		
		List<Action> initActions = this.filterAction(actions, "init");
		if(initActions == null || initActions.isEmpty()) 
			return;
		
		outLoop: while(true) {
			for(Action action : initActions) {
				Result result = new Result();
				this.invoke(device, action, result);
				if(!result.isSuccessed()) 
					continue outLoop;
			}
			break;
		}
	}
	
	//执行status=running状态的action(循环执行)
	public void runRunningAction(JadbDevice device, List<Action> actions) {
		
		List<Action> runningActions = this.filterAction(actions, "running");
		if(runningActions == null || runningActions.isEmpty()) 
			return;
		
		while(true) {
			for(Action action : runningActions) {
				Result parentResult = new Result();
				//执行父action
				this.invoke(device, action, parentResult);
				if(parentResult.isSuccessed()) {
					//执行子action
					this.runChildAction(device, action, parentResult);
				} else break;
					
			}
		}
	}
	
	//执行子action
	public void runChildAction(JadbDevice device, Action action, Result parentResult) {
		
		List<Action> childActions = action.getChildActions();
		if(childActions == null) return;
		String actionStateName = action.getActionStateName();
		if(StringUtil.isEmpty(actionStateName)) return;
		ActionState actionState = (ActionState) applicationContext.getBean(actionStateName);

		if(action.getType().equals(StaticValue.TYPE_SEARCH_TARGET_NODE)) {

			List<Coord> coords = actionState.getCoords();
			outLoop: for(Coord coord : coords) {
				for(Action childAction : childActions) {
					
					Result childResult = new Result();
					childAction.setParentTochildLocation(coord);
					
					this.invoke(device, childAction, childResult);
					if(!childResult.isSuccessed()) continue outLoop;
				}
			}
		}
		
	}
	
	//执行操作
	public void invoke(JadbDevice device, Action action, Result result) {
		log.info("current action: {}", action.getName());
		QuoteActionType type = (QuoteActionType) applicationContext.getBean(action.getType());
		type.operate(device, action, result);
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
