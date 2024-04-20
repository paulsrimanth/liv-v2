package com.libmanagement.repository.service;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.libmanagement.model.PowerUser;
import com.libmanagement.repository.PowerUserRepository;

@Component
public class PowerUserImpl implements PowerUserInterface{
	
	PowerUserRepository poweruser;
	public PowerUserImpl(PowerUserRepository poweruser) {
		super();
		this.poweruser = poweruser;
	}
	@Override
	public PowerUser CreatePowerUser(PowerUser user) {
		
		return poweruser.save(user);
	}
	@Override
    public PowerUser getPowerUserById(Integer id) {
		System.out.println("in get poweruser by id "+ id);
        return poweruser.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("PowerUser not found"));
    }
	
	
//	Optional<PowerUser>findByemail(String email){
//		return poweruser.findby
//		
//	}

}
