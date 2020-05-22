/**
 * 
 */
/**
 * @author hilbp
 * 
 * Back:返回操作
 * click：通过yml配置的坐标、父action传入的坐标进行点击操作
 * clickTargetNode：通过xpath在ui文件找到目标node的坐标，进行点击，是个数组
 * open：打开activity
 * searchTargetNode：通过xpath在ui文件找到目标node的坐标并保存在list中
 * sendMessageToInput：点击文本框获取焦点，唤起输入法
 * swipe：滑动屏幕
 * 
 * @desc: 当一个action执行失败时，如果是父action则第一步开始执行；如实是子action，如果配置了异常类则进行修复，如果没有配置异常则跳过该子action。
 * 
 * 
 * 
 */
package com.hilbp.adb.action.type.simple;

