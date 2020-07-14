package com.hilbp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.hilbp.adb.yml.ActionData;
import com.hilbp.adb.yml.ActionDataBinder;

import lombok.extern.slf4j.Slf4j;
 
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AdbApplication.class)
@Slf4j
public class ActionDataBinderTest {
	
	@Autowired
	private ActionDataBinder actionDataBinder;
	
	
	
    @Test
    public void test() {
    	String prefix = "soul-comment";
    	ActionData actionData = actionDataBinder.actionBinderYml(prefix);
    	log.info("======: {}", actionData);
    	log.info("234");
    }
    
   
   
    
}