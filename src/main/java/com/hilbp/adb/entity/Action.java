package com.hilbp.adb.entity;

import java.util.List;

import lombok.Data;

@Data
public class Action {
	
	private int order; //序号
	private String name; //名称
	private String status; //执行状态
	private String type; //操作类型
	private String currentActivity; //当前activity
	private String targetActivity; //目标activity
	
	//when type=click
	private Coord location; //点击的坐标位置 type=click
	
	//when type=swipe
	private Coord offset; //滑动的偏移量
	private String direction; //滑动方向：上下左右
	
	//when type=clickByBounds
	private String xpath;
	
	//when type=custom 反射调用
	private String customBeanName; //类名
	
	//when type=setIme
	private String imeName;
	
	//when type=searchTargetNode
	private String uiSavePath; //pull ui布局文件保存位置
	private String actionStateName; //一个bean，用于向子action传递上下文
	
	private Coord parentTochildLocation; //从父action传递给子action的坐标
	
	private Boolean isNotGetNewUi; //是否获取新的布局文件
	
	private List<Action> childActions; //一个action包含的子action集合
	
	//when type=MatchTemplate
	private String sourcePath; //原图
	private String templatePath; //目标图
	private float threshold; //阈值
	
	
}
