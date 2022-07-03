package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {
	
	/**Display login screen*/
	@GetMapping("/login")
	public String getLogin() {
		return "login/login.html";
	}
	
	/**Redirect to user list screen*/
	@PostMapping("/login")
	public String postLogin() {
		return "redirect:/user/list";
	}
}
