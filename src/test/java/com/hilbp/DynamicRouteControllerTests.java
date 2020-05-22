package com.hilbp;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.alibaba.fastjson.JSONObject;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = AdbApplication.class)
public class DynamicRouteControllerTests {
	
	private MockMvc mockMvc;
	//启用web上下文
    @Autowired
    private WebApplicationContext context;  
    
    @Before
    public void init() {
    	mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }
	
	@Test
	public void create() throws Exception{
		Map<String, Object> content = new HashMap<String, Object>();
		content.put("id", "test3");
		content.put("enabled", true);
		content.put("path", "/test3/**");
		content.put("retryable", false);
		content.put("serviceId", "test3");
		content.put("stripPrefix", true);
		
		String data = JSONObject.toJSONString(content);
		mockMvc.perform(
				MockMvcRequestBuilders.post("/route/create")
				.accept(MediaType.APPLICATION_JSON_UTF8)
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(data))
			.andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
	}
	
	@Test
	public void update() throws Exception {
		Map<String, Object> content = new HashMap<String, Object>();
		content.put("id", "test1");
		content.put("enabled", true);
		content.put("path", "/test1/**");
		content.put("retryable", false);
		content.put("serviceId", "test1");
		content.put("stripPrefix", true);
		
		String data = JSONObject.toJSONString(content);
		mockMvc.perform(
				MockMvcRequestBuilders.post("/route/update")
				.accept(MediaType.APPLICATION_JSON_UTF8)
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(data))
			.andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
	}
	
	@Test
	public void delete() throws Exception {
		String id = "test2";
		mockMvc.perform(
				MockMvcRequestBuilders.get("/route/delete/" + id)
				.accept(MediaType.APPLICATION_JSON_UTF8))
			.andDo(print()).andReturn().getResponse().getContentAsString();
	}
	
	@Test
	public void list() throws Exception {
		mockMvc.perform(
			MockMvcRequestBuilders.get("/route/list")
			.accept(MediaType.APPLICATION_JSON_UTF8))
		.andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
	}
	
	

}
