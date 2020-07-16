package com.hilbp.adb.action.type2.entity.Impl;

import com.hilbp.adb.action.type2.entity.ActionEntity;
import com.hilbp.adb.entity.Coord;
import lombok.Data;

@Data
public class ClickEntity extends ActionEntity {
    private Coord location; //点击的坐标位置 type=click
    private Coord parentTochildLocation; //从父action传递给子action的坐标
}
