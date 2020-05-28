# app-bot-java

app-bot-java

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

