package com.hilbp.adb.util;

public class StaticValue {
	
	// type
	public final static String TYPE_OPEN = "open"; //打开app
	public final static String TYPE_ClICK = "click"; //点击
	public final static String TYPE_ClICK_BY_BOUNDS = "clickByBounds"; //点击通过bounds
	public final static String TYPE_BOUNDS_TO_COORD = "boundsToCoord"; //通过bounds获取坐标集合
	public final static String TYPE_SEARCH_TARGET_NODE = "searchTargetNode";
	public final static String TYPE_SWIPE = "swipe"; //滑动
	public final static String TYPE_BACK = "back"; //返回
	public final static String TYPE_CUSTOM = "custom"; //自定义复杂操作
	
	public final static String TYPE_SEARCH_TARGET_NODE_SIMPLE = "simpleSearchTargetNode";
	
	// status
	public final static String STATUS_INIT = "init"; //初始化，只执行一次
	public final static String STATUS_RUNNING = "running"; //循环执行
	public final static String StATUS_FINISHED = "finished"; //操作完成，只执行一次
	public final static String STATUS_EXCEPTION = "exception"; //发生异常时执行一次做清理
	
	// direction when type=swipe
	public final static String DIRECTION_UP = "up"; 
	public final static String DIRECTION_DOWN = "down"; 
	public final static String DIRECTION_LEFT = "left"; 
	public final static String DIRECTION_RIGHT = "right"; 
	
	//提示消息
	public final static String MSG_COORD_EMPTY = "未找到目标node的坐标";
	public final static String MSG_TEXT_EMPTY = "你在说什么啊？";
	
}
