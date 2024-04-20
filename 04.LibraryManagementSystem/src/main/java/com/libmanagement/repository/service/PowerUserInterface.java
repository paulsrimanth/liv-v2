package com.libmanagement.repository.service;

import java.util.Optional;

import com.libmanagement.model.PowerUser;

public interface PowerUserInterface {
	
	PowerUser CreatePowerUser(PowerUser user);
	public PowerUser getPowerUserById(Integer id);

}
