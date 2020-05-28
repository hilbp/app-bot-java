package com.hilbp.adb.yml;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.Bindable;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import com.hilbp.web.config.YamlPropertySourceFactory;

@Component
@PropertySource(
		factory = YamlPropertySourceFactory.class, 
		value = {
				"classpath:configScreen/2160x1080.yml",
				"classpath:configAction/doyin-cancel-like.yml",
				"classpath:configAction/doyin-comment-docomment.yml",
				"classpath:configAction/doyin-comment-dolike.yml",
				"classpath:configAction/doyin-video-comment.yml",
				"classpath:configAction/soul-auto-chat.yml",
				"classpath:configAction/soul-comment.yml",
				"classpath:configAction/soul-do-like.yml",
				"classpath:configAction/soul-star-match.yml"
		}, 
		encoding = "utf-8")
public class ActionDataBinder {
	
	@Autowired
	private ApplicationContext applicationContext;

	public ActionData actionBinderYml(String prefix) {
		Binder binder = Binder.get(applicationContext.getEnvironment());
		ActionData actionData = binder.bind(prefix, Bindable.of(ActionData.class)).get();
		return actionData;
	}
}
