package com.hilbp.web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hilbp.adb.yml.ActionData;
import com.hilbp.adb.yml.ActionDataBinder;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/yml")
@Slf4j
public class YmlTestController {
	
	@Autowired
	private ActionDataBinder actionDataBinder;
	
	@RequestMapping("/index")
	public ResponseEntity<Object> index() {
		ActionData actionData = actionDataBinder.actionBinderYml("soul-do-like");
		return new ResponseEntity<Object>(actionData, HttpStatus.OK);
	}

}
