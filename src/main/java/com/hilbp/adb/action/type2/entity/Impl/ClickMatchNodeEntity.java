package com.hilbp.adb.action.type2.entity.Impl;

import com.hilbp.adb.action.type2.entity.ActionEntity;
import lombok.Data;

@Data
public class ClickMatchNodeEntity extends ActionEntity {

    private String sourcePath; //原图
    private String templatePath; //目标图
    private float threshold; //阈值
}
