package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserListController {
	/**Display user list screen*/
	@GetMapping("/list")
	public String getUserList() {
		//Display user list screen
		return "user/list.html";
	}
}
