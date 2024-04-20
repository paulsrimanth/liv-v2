package com.libmanagement.repository;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.libmanagement.model.Admin;
public interface Adminrepository extends JpaRepository<Admin,Integer>
{
	Optional<Admin>findByEmail(String email);
	List<Admin> findByPowerId(Integer powerid);
	
}
