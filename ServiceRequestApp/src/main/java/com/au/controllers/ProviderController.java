package com.au.controllers;

import com.au.models.ProviderEntity;
import com.au.services.ProviderEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/provider")
public class ProviderController {
	@Autowired
	ProviderEntityService provider;

	@GetMapping("/hello")
	public String getHello() {
		return "hello";
	}

	@PostMapping("/signup")
    public void addUser(@RequestBody ProviderEntity user) {
		System.out.println(user.getProviderName());
        provider.saveProvider(user);
    }

}