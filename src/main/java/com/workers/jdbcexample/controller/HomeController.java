package com.workers.jdbcexample.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.workers.jdbcexample.util.Utils;

@RestController
public class HomeController {
	@GetMapping("/")
	public String index() {
return "Hello Visitor!<br>Visiting time: "+Utils.getCurrentTimestamp();
	}
}
