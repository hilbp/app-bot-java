package com.hilbp.adb.action.type2.entity.Impl;

import com.hilbp.adb.action.type2.entity.ActionEntity;
import lombok.Data;

@Data
public class ClickTargetNodeEntity extends ActionEntity {

    private Boolean isNotGetNewUi; //是否获取新的布局文件
    private String uiSavePath; // ui布局文件保存位置
}
