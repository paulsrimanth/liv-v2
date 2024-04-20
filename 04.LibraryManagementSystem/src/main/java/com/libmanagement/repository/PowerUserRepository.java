package com.libmanagement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.libmanagement.model.PowerUser;

public interface PowerUserRepository extends JpaRepository<PowerUser,Integer> 
{

	Optional<PowerUser>findByEmail(String email);
}
