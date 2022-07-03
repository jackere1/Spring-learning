package com.example.demo.hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {
	
	@Autowired
	private HelloService service;
	
	@GetMapping("heelo")
	public String homeshit() {
		return "hello.html";
	}
	
	@PostMapping("heelo")
	public String responeshit(@RequestParam("request") String str, Model model) {
		model.addAttribute("sample", str);
		return "hello/response.html";
	}
	
	@PostMapping("/heelo/db")
	public String postDbRequest(@RequestParam("input2") String id, Model model) {
		//Search one
		Employee employee = service.getEmployee(id);
		
		//Save Search Results to Model
		model.addAttribute("employee", employee);
		
		//Screen transition to db.html
		return "hello/db.html";
	}
}
