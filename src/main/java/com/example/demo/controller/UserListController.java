package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.domain.user.model.MUser;
import com.example.demo.domain.user.service.UserService;

@Controller
@RequestMapping("/user")
public class UserListController {
	
	@Autowired
	private UserService userService;	
	
	/**Display user list screen*/
	@GetMapping("/list")
	public String getUserList(Model model) {
		
		//Get user list
		List<MUser> userList = userService.getUsers();
		
		//Registered in Model
		model.addAttribute("userList", userList);
		
		//Display user list screen
		return "user/list.html";
	}
}
