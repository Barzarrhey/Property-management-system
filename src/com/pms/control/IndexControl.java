package com.pms.control;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexControl {
	
	@RequestMapping("/tologin.html")
	public String index(){
		return "login";
	}
	@RequestMapping("/top.html")
	public String index3() {
		return "top";
	}
	@RequestMapping("/down.html")
	public String index4() {
		return "down";
	}
	@RequestMapping("/center.html")
	public String index5() {
		return "center";
	}
	@RequestMapping("/left.html")
	public String index6() {
		return "left";
	}
	@RequestMapping("/right.html")
	public String index1() {
		return "right";
	}
	
	
}
