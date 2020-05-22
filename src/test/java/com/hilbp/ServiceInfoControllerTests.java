package com.hilbp;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

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


@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceInfoControllerTests {
	
	private MockMvc mockMvc;
	//启用web上下文
    @Autowired
    private WebApplicationContext context;
    
    
    @Before
    public void init() {
    	mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }
   
	@Test
	public void detail() throws Exception {
	    mockMvc.perform(MockMvcRequestBuilders.get("/microservice/detail/1")
	    		.accept(MediaType.APPLICATION_JSON_UTF8)).andDo(print());
	}
	
	@Test
	public void delete() throws Exception {
		
		mockMvc.perform(MockMvcRequestBuilders.get("/microservice/delete/1")
                .accept(MediaType.APPLICATION_JSON_UTF8)).andDo(print());
	}
	
	@Test
	public void list() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/microservice/list")
                .accept(MediaType.APPLICATION_JSON_UTF8)).andDo(print());
		
	}

}
