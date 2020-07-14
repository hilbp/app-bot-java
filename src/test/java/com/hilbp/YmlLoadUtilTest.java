package com.hilbp;

import java.io.IOException;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.boot.context.properties.bind.Bindable;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.boot.env.YamlPropertySourceLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.test.context.junit4.SpringRunner;

import com.hilbp.adb.yml.ActionData;

import lombok.extern.slf4j.Slf4j;
 
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AdbApplication.class)
@Slf4j
public class YmlLoadUtilTest implements ResourceLoaderAware {
	
	@Autowired
	private ApplicationContext applicationContext;
	
	@Autowired
	private ConfigurableApplicationContext configurableApplicationContext;
	
	@Autowired
	ConfigurableEnvironment environment;
	
	private ResourcePatternResolver resourceLoader;
	
    @Test
    public void test() {
    	
        this.load1();
    }
    
    public void load2() {
    	try {
			Resource[] resouces = applicationContext.getResources("classpath*:configAction/*.yml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    
    public void load1() {

    	try {
//    		ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
//			Resource[] resources = resolver.getResources("classpath*:configAction/*.yml");
//    		Resource[] resources = applicationContext.getResources("classpath*:configAction/*.yml");
    		Resource[] resources = this.resourceLoader.getResources("classpath*:configAction/*.yml");
			YamlPropertiesFactoryBean factory = new YamlPropertiesFactoryBean();
			
			PropertiesPropertySource propertiesPropertySource;
			for(Resource r : resources) { 
				factory.setResources(r);
	            factory.afterPropertiesSet();
	            propertiesPropertySource = new PropertiesPropertySource(r.getFilename(), factory.getObject());
				environment.getPropertySources().addLast(propertiesPropertySource);
			}
			
			Binder binder = Binder.get(applicationContext.getEnvironment());
			ActionData actionData = binder.bind("soul-comment", Bindable.of(ActionData.class)).get();
			
			log.info("====: {}", actionData);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void load() {
    	ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        try {
        	YamlPropertySourceLoader loader = new YamlPropertySourceLoader();
			Resource[] resources = resolver.getResources("classpath*:configAction/*.yml");
			for(Resource r : resources) {
				List<PropertySource<?>> list = loader.load(r.getFilename(), r);
				environment.getPropertySources().addLast(list.get(0));
			}
			
			
			Binder binder = Binder.get(applicationContext.getEnvironment());
			ActionData actionData = binder.bind("soul-comment", Bindable.of(ActionData.class)).get();
			
			log.info("====: {}", actionData);
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
    }

	@Override
	public void setResourceLoader(ResourceLoader resourceLoader) {
		// TODO Auto-generated method stub
		this.resourceLoader = (ResourcePatternResolver) resourceLoader;
	}
   
    
}