package com.hilbp.adb.entity;

import lombok.Data;

@Data
public class ManualControlEntity {
    private String type; //操作类型：对应于StaticValue的Munual_type_**的值
    private String text; //输入文本
    private Coord coord; //点击坐标
    private boolean operated = false; //用户是否有操作 false用户未操作；true用户有操作
}
