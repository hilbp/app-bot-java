package com.hilbp.adb.state;

import java.util.List;

import org.springframework.stereotype.Component;

import com.hilbp.adb.entity.Node;

/**
 * 用于保存一个action提取到的text信息往另一个action传递
 * @author hilbp
 *
 */
@Component
public class SaveTextActionState extends AbstractActionState<List<Node>> {}
