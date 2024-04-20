package com.libmanagement.repository.service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;

import com.libmanagement.model.Admin;
import com.libmanagement.model.AuthenticationResponse;
import com.libmanagement.model.PowerUser;
import com.libmanagement.repository.Adminrepository;
import com.libmanagement.repository.PowerUserRepository;
@Service
public class AuthenticationService {
	
	private final PowerUserRepository repository;
	private final PasswordEncoder passwordEncoder;
	private final Jwtservice jwtservice;
	private final AuthenticationManager authenticationManager;
	private final Adminrepository adminrepository;
	private final PowerUserImpl poweruserimpl;


	public AuthenticationService(PowerUserRepository repository, PasswordEncoder passwordEncoder, Jwtservice jwtservice,
			AuthenticationManager authenticationManager, Adminrepository adminrepository, PowerUserImpl poweruserimpl) {
		super();
		this.repository = repository;
		this.passwordEncoder = passwordEncoder;
		this.jwtservice = jwtservice;
		this.authenticationManager = authenticationManager;
		this.adminrepository = adminrepository;
		this.poweruserimpl = poweruserimpl;
	}
	public PowerUser register(PowerUser request) {
		PowerUser poweruser = new PowerUser();
		poweruser.setId(request.getId());
		poweruser.setEmail(request.getEmail());
		poweruser.setPassword(passwordEncoder.encode(request.getPassword()));
		poweruser.setPassReset(request.getPassReset());
		poweruser.setAdminData(request.getAdminData());
		poweruser = repository.save(poweruser);

		return poweruser;
	}
	public AuthenticationResponse authentice(PowerUser request) {
		System.out.println(request.getEmail());
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(
					request.getEmail(),
					request.getPassword()
					)
				);
		}
		catch(Exception ae) {
			System.out.println("error at login"+ae.getMessage());
		}
	
		System.out.println("before finding");
		PowerUser user =repository.findByEmail(request.getEmail()).orElseThrow();
		System.out.println("after user");
		//System.out.println(user);
		System.out.println(user.getEmail());
		String token = jwtservice.generateToken(user);
		System.out.println(token);
		return new AuthenticationResponse(token);
		
	}
	/*gets poweruser data from login
	 * after login the data is stored
	 * from data we extract id and and through the id we insert the admin
	 */
	public PowerUser getpowerdata(PowerUser request) {
		PowerUser poweruser = repository.findByEmail(request.getEmail()).orElseThrow();
		//		PowerUser user = new PowerUser(); commented for error
		
		return poweruser;
		
	}
	//power user registers the admin ,this creates an admin
	public  Admin AdminRegister(Admin request) {
	
		Admin admin = new Admin();
		Admin admindata = new Admin();
		admindata.setPowerId(request.getPowerId());
		Integer poweridtofind = admindata.getPowerId();
		System.out.println(request.getPowerId());
		System.out.println("powerid to find "+poweridtofind);
		PowerUser poweruser = poweruserimpl.getPowerUserById(poweridtofind);
		System.out.println("poweruser from findinf id : "+poweruser);
		admin.setFirstname(request.getFirstname());
		admin.setLastname(request.getLastname());
		admin.setEmail(request.getEmail());
		admin.setPassword(request.getPassword());
		admin.setPowerId(request.getPowerId());
		admin.setPoweruser(poweruserimpl.getPowerUserById(poweridtofind));
		admin = adminrepository.save(admin);
		System.out.println("auth service admin register 2");
//		PowerUser poweruser = new PowerUser();
//		poweruser.setEmail(request.getEmail());
//		poweruser.setPassword(passwordEncoder.encode(request.getPassword()));
//		poweruser.setPassReset(request.getPassReset());
//		poweruser = repository.save(poweruser);
		return admin;

	}
	public Admin adminregbypoweruser(PowerUser request) {
		Admin admin = new Admin();
		List<Admin> adminlist = request.getAdminData();
//		System.out.println("ad list[0]"+adminlist.get+(0).getFirstname());
//		admin.setFirstname(adminlist.get(0).getFirstname());
//		admin.setLastname(adminlist.get(0).getLastname());
//		admin.setEmail(adminlist.get(0).getEmail());
//		admin.setPassword(adminlist.get(0).getPassword());
//		admin.setPowerId(request.getId());
//		System.out.println(adminlist);commented for error
//		System.out.println("in admin reg " + admin.getEmail());	
		return null;
				//adminrepository.save(admin);
		
	}

}
