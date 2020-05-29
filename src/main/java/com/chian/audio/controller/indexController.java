package com.chian.audio.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
public class indexController {

	@RequestMapping("index")
	public String index(){
		System.out.println("come here");
		return "fan";
	}
	
	@RequestMapping("getIndex")
	@ResponseBody
	public String getIndex(){
		return "this is my first jenkins project";
	}
		
	@RequestMapping("methodTwo")
	@ResponseBody
	public String methodTwo(){
		return "hehehe";
	}
	
	@RequestMapping("methodThree")
	@ResponseBody
	public String methodThree(){
		return "methos three";
	}
	
	@RequestMapping("methodFour")
	@ResponseBody
	public String methodFour(){
		return "hahaha";
	}

	@RequestMapping("testCase")
	@ResponseBody
	public String testCase(){
		return "test case";
	}
	
}
