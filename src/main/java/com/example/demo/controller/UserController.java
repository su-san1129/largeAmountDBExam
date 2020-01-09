package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {
	
	@RequestMapping("/login")
	public String toLogin() {
		return "login";
	}
	@RequestMapping("/register")
	public String toRegister() {
		return "register";
	}

}
