package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
	
	/**Display login screen*/
	@GetMapping("/login")
	public String getLogin() {
		return "login/login.html";
	}
	
}
