# app-bot-java

github在国内网络有点卡，图片加载不出来的话来这里看简介：
https://www.jianshu.com/p/1de7e0e547fe
app-bot-java

### 概述

之前用Python实现过一版，但由于对Python不熟，后期代码扩展时很棘手。由于一直用java做开发，所以抱着学习的心态，用java做了重构，考虑了一些设计模式，相对之前用Python实现的，好了很多。java面向对象设计的思想再结合设计模式，对应用后期的迭代、扩展都是很好的。

![主界面](/src/main/resources/static/assert/img/3.png)

### 功能

  soul：灵魂匹配、机器人聊天、对瞬间点赞、对瞬间评论
  抖音：对视频的评论进行点赞

> 对瞬间评论：提取瞬间内容，调用AI接口进行语义识别，然后调用机器接口获取应答内容，之后评论
>
> 机器人聊天：获取对方消息内容，调用机器人接口语义识别且回复

### 环境要求

  电脑端OS：目前仅支持windows平台，已在win10测试正常

  Python：3.0+

  adb：version 1.0.41

  手机OS：安卓，无需root

### 涉及技术或框架

- 后端spring boot、前端iview
- 前后端实时通信websocket、图像目标识别JavaCV



### 一些action类介绍：

`ClickTargetNode`：获取布局文件搜索目标node，保存在list中，遍历list对每个node进行点击操作。当前点击出现异常时，点击下一个符合条件的node。适用于同一个页面操作，不适合点击后页面发生跳转的操作。需要传递的参数示例：

- order: 3
- name: 点击文本框获取输入焦点
- status: running
- type: clickTargetNode
- not-get-new-ui: false #是否获取新的ui布局文件，根据上下文决定，对响应时间有一定的影响
- ui-save-path: E:/adb/uidump.xml
- xpath: //node[@resource-id='cn.soulapp.android:id/et_sendmessage']
- current-activity: ${config.soul.activity.chat-window-activity.name}
- target-activity: ${config.soul.activity.chat-window-activity.name}

