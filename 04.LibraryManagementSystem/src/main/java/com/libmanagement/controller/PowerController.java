package com.libmanagement.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.libmanagement.model.Admin;
import com.libmanagement.model.PowerUser;
import com.libmanagement.repository.service.PowerAuthService;
@CrossOrigin
@RestController("/power")
public class PowerController
{
	PowerAuthService powerauthservice;
	
	
    public PowerController(PowerAuthService powerauthservice) {
		super();
		this.powerauthservice = powerauthservice;
	}

//	@PostMapping("/getadmins")
//    public ResponseEntity<Admin> adminreg(@RequestBody PowerUser request)
//    {
//		System.out.println("in get dmins");
//    	return ResponseEntity.ok(powerauthservice.getadminsbypowerid(request));
//    	
//    }

    
}
