package com.libmanagement.repository.admin;
import com.libmanagement.model.Admin;
import com.libmanagement.repository.Adminrepository;
public class AdminInterfaceImpl implements AdminInterface {
	//admin repo extends jpa and contains one unimplemented method
	Adminrepository adminrepo;
	//creating an admin 
	@Override
	public Admin createAdmin(Admin admin) {
		
		return adminrepo.save(admin);
	}

}
