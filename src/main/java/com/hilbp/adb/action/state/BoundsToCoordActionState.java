package com.hilbp.adb.action.state;

import org.springframework.stereotype.Component;

import com.hilbp.adb.action.base.ActionState;

/**
 * 用于保存从父action向子action传递的父action执行结果的状态数据
 * @author hilbp
 *
 */
@Component
public class BoundsToCoordActionState extends ActionState {}
