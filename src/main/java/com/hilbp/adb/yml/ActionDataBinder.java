package com.hilbp.adb.yml;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.Bindable;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Component;

import com.hilbp.web.config.YamlPropertySourceFactory;

import lombok.extern.slf4j.Slf4j;

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
				"classpath:configAction/soul-star-match.yml",
				"classpath:configAction/screen-mirror.yml"
		}, 
		encoding = "utf-8")
@Slf4j
public class ActionDataBinder {
	
	public ActionDataBinder() {
		log.info("============");
		ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		try {
			Resource[] resources = resolver.getResources("classpath*:configAction/*.yml");
			for (Resource r : resources) {
	            log.info("path:{}", r.getFilename());
	            
	            Properties properties = PropertiesLoaderUtils.loadProperties(r);
	            @SuppressWarnings("unchecked")
				Enumeration<String> names = (Enumeration<String>) properties.propertyNames();
	            while (names.hasMoreElements()) {
	                log.info("name:{}", names.nextElement());
	            }
	        } 
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	}
	
	@Autowired
	private ApplicationContext applicationContext;

	public ActionData actionBinderYml(String prefix) {
		Binder binder = Binder.get(applicationContext.getEnvironment());
		ActionData actionData = binder.bind(prefix, Bindable.of(ActionData.class)).get();
		return actionData;
	}
}
