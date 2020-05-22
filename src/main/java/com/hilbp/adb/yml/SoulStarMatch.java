package com.hilbp.adb.yml;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import com.hilbp.adb.entity.Action;
import com.hilbp.web.config.YamlPropertySourceFactory;

import lombok.Data;

@Component
@PropertySource(
		factory = YamlPropertySourceFactory.class, 
		value = {
				"classpath:configAction/soul-star-match.yml",
//				"classpath:configScreen/2160x1080.yml"
		}, 
		encoding = "utf-8")
@ConfigurationProperties(prefix = "soul-star-match")
@Data
public class SoulStarMatch {
	
	private String name;
	private String desc; 
	private String title;
	private List<Action> actions;

}
