package com.hilbp.adb.action.type.base;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.util.Assert;

import com.hilbp.adb.entity.Action;
import com.hilbp.adb.entity.Coord;
import com.hilbp.adb.entity.ActionResult;
import com.hilbp.adb.state.ActionState;
import com.hilbp.adb.util.AdbShellUtil;
import com.hilbp.adb.util.StringUtil;
import com.hilbp.adb.util.UiAutoMatorUtil;

import lombok.extern.slf4j.Slf4j;
import se.vidstige.jadb.JadbDevice;

@Slf4j
public abstract class ActionType implements Type {
	
	@Autowired
	protected ApplicationContext applicationContext;
	
	@Autowired
	protected AdbShellUtil adbShellUtil;
	
	public abstract void operate(JadbDevice device, Action action);
	public abstract void operate(JadbDevice device, Action action, ActionResult resutl);
	
	//命令之前判断是否满足执行条件
	public void beforExecuteShell(JadbDevice device, Action action) {
		
		log.info("→   判断命令执行的前置条件");
		if(StringUtil.isEmpty(action.getCurrentActivity()))
			return;
		
		Assert.isTrue(adbShellUtil.isTargetActivity(device, action.getCurrentActivity()),
				String.format("不满足执行条件：%s", action.getName()));
	}
	
	//发送命令后等待执行完成或超时
	public void afterExecuteShell(JadbDevice device, Action action) {
		
		log.info("→   等待命令执行完成");
		
		if(StringUtil.isEmpty(action.getTargetActivity())) 
			return;
		
		int i = 0;
		while(i < 6) {
			if(adbShellUtil.isTargetActivity(device, action.getTargetActivity()))
				return;
			try {
				Thread.sleep(1000);
				i++;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		Assert.isTrue(i < 5, String.format("命令执行失败:：%s", action.getName()));
	}
	
	// 保存状态(一般情况是通过ui布局文件获取的坐标)
	public void saveActionState(Action action) {
		
		String actionStateName = action.getActionStateName();
		if(StringUtil.isEmpty(actionStateName)) return;
		ActionState ActionState = (ActionState)applicationContext.getBean(actionStateName);
		ActionState.clear();
		ActionState.setState(action, UiAutoMatorUtil.getTargetNode(action), null);
	}
	
	// 保存状态(一般情况是通过截屏匹配目标控件获取的坐标)
	public void saveActionState(Action action, List<Coord> coords) {
		
		String actionStateName = action.getActionStateName();
		if(StringUtil.isEmpty(actionStateName)) return;
		ActionState ActionState = (ActionState)applicationContext.getBean(actionStateName);
		ActionState.clear();
		ActionState.setState(action, null, coords);
	}
	
	//点击操作
	public void click(JadbDevice device, Action action, List<Coord> coords) {
		
		if(coords == null || coords.isEmpty()) 
			return;
		
		for(Coord coord : coords) {
			try {
				 
				this.beforExecuteShell(device, action);
				adbShellUtil.click(device, coord.getX(), coord.getY());
				Thread.sleep(1000);
				this.afterExecuteShell(device, action);
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	//命令之前判断是否满足执行条件
	public boolean beforExecuteShell(JadbDevice device, Action action, boolean bool) {
		
		log.info("→   判断命令执行的前置条件");
		boolean flg = false;
		if(StringUtil.isEmpty(action.getCurrentActivity())
				|| adbShellUtil.isTargetActivity(device, action.getCurrentActivity()))
			flg = true;
		return flg;
	}
	
	//发送命令后等待执行完成或超时
	public boolean afterExecuteShell(JadbDevice device, Action action, boolean bool) {
		
		log.info("→   等待命令执行完成");
		boolean flg = false;
		int i = 0;
		while(i < 5) {
			try {
				if(StringUtil.isEmpty(action.getTargetActivity())
						|| adbShellUtil.isTargetActivity(device, action.getTargetActivity())) {
					flg = true;
					break;
				}
				Thread.sleep(3000);
				i++;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		return flg;
	}
}
