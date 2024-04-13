package com.softwareverification.realestate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.softwareverification.realestate.service.FetchDetails;

@RestController
public class FirstTableController {

	@Autowired
	FetchDetails details;
	
	@GetMapping(value = "/firstName")
	public String getFirstName() {
		return details.getFirstName();
	}
}
