package com.libmanagement.repository.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.libmanagement.model.Admin;
import com.libmanagement.model.PowerUser;
import com.libmanagement.repository.Adminrepository;
import com.libmanagement.repository.PowerUserRepository;
@Service
public class PowerAuthService 
	
{
	
	Adminrepository adminrepository;
	PowerUserRepository poweruserrepository;

	public PowerAuthService(Adminrepository adminrepository, PowerUserRepository poweruserrepository) {
		super();
		this.adminrepository = adminrepository;
		this.poweruserrepository = poweruserrepository;
	}



	public List<Admin> getadminsbypowerid(PowerUser request) {
		
		PowerUser power = new PowerUser();
		power.setId(request.getId());
		Integer id  = power.getId();
		List<Admin> admindata = adminrepository.findByPowerId(id);
		Admin admin = new Admin();
		System.out.println("admindata");
		System.out.println(admindata.get(0));
		return adminrepository.findByPowerId(id);
	}
}
