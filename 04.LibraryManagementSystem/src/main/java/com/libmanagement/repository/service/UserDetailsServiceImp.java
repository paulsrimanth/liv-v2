package com.libmanagement.repository.service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.libmanagement.model.PowerUser;
import com.libmanagement.repository.PowerUserRepository;
@Service
public class UserDetailsServiceImp implements UserDetailsService {

	private PowerUserRepository repository;

	private UserDetailsImpl userdetailsimp;

	
	public UserDetailsServiceImp(PowerUserRepository repository, UserDetailsImpl userdetailsimp) {
		super();
		this.repository = repository;
		this.userdetailsimp = userdetailsimp;
	}


	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		System.out.println(email);
		PowerUser u=new PowerUser();
		List<GrantedAuthority> list = new ArrayList();
		try {
		Optional<PowerUser> user=repository.findByEmail(email);
		u=user.get();
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		System.out.println(u.getEmail()+" "+u.getPassword());
		User us=new User(u.getEmail(),u.getPassword(),list);
		userdetailsimp.setEmail(u.getEmail());
		userdetailsimp.setPassword(u.getPassword());
		System.out.println("us"+us.getUsername());
		return us;
				
	}


}