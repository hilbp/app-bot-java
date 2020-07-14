package com.hilbp.adb.state;

import java.util.List;

import org.springframework.stereotype.Component;

import com.hilbp.adb.entity.Node;

/**
 * 用于保存从父action向子action传递的父action执行结果的状态数据
 * @author hilbp
 *
 */
@Component
public class BoundsToCoordActionState extends AbstractActionState<List<Node>> {}
