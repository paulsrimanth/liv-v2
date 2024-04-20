package com.libmanagement.controller;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.libmanagement.model.Admin;
import com.libmanagement.model.AuthenticationResponse;
import com.libmanagement.model.PowerUser;
import com.libmanagement.repository.service.AuthenticationService;
import com.libmanagement.repository.service.PowerAuthService;

@CrossOrigin
@RestController
public class AuthenticationController {
	
	 private final AuthenticationService authService;
	 private final  PowerAuthService powerauthservice;
	 

		public AuthenticationController(AuthenticationService authService, PowerAuthService powerauthservice) {
		super();
		this.authService = authService;
		this.powerauthservice = powerauthservice;
	}
		
		/** gets admins that are added by poweruser
		 * this reponse will be shown to poweruser when he log's in
		 */
		@PostMapping("/getadmins")
	    public ResponseEntity<List<Admin>> adminregs(@RequestBody PowerUser request)
	    {
			System.out.println("in get main controllers");
			List<Admin> adminl = powerauthservice.getadminsbypowerid(request);
	    	return ResponseEntity.ok(adminl);
	    	
	    }
		@PostMapping("/register")
	    public ResponseEntity<PowerUser> register(
	            @RequestBody PowerUser request
	            ) {
	    	
	        return ResponseEntity.ok(authService.register(request));
	    }

	    @PostMapping("/login")
	    public ResponseEntity<AuthenticationResponse> login(@RequestBody PowerUser request) {
	    	System.out.println(request.getEmail());
	        return ResponseEntity.ok(authService.authentice(request));
	    }
	    @PostMapping("/adminregister")
	    public ResponseEntity<Admin> adminregister(@RequestBody Admin request){
	    	System.out.println("inside admin register");
//	    	System.out.println("in controller "+request);
	    	System.out.println("controller req "+request.getPowerId());
			return ResponseEntity.ok(authService.AdminRegister(request)) ;
	    	
	    }
	    
	    @PostMapping("/adminreg")
	    public ResponseEntity<Admin> adminreg(@RequestBody PowerUser request)
	    {
			
	    	return ResponseEntity.ok(authService.adminregbypoweruser(request));
	    	
	    }
	    @PostMapping("/getpowerdata")
	    public ResponseEntity<PowerUser> getpowerdatafterlogin(@RequestBody PowerUser request)
	    {
	    	return ResponseEntity.ok(authService.getpowerdata(request));
	    }

	    
	   


}
