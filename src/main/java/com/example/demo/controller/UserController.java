package com.example.demo.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
	
	@RequestMapping("/login")
	public String toLogin(Model model, @RequestParam(required = false) String error) {
		if (error != null) {
			model.addAttribute("loginError", "メールアドレスまたはパスワードが違います。");
		}
		return "login";
	}
	@RequestMapping("/register")
	public String toRegister() {
		return "register";
	}

}
