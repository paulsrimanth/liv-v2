package com.libmanagement.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@JsonIgnoreProperties({"adminData"})
public class PowerUser {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String email;
	private String password;
	private String passReset;
	@OneToMany(mappedBy = "poweruser")
	private List<Admin> adminData;
	@Override
	public String toString() {
		return "poweruser";
	}
	
	//Pid is poweruserId it will be added in Admin table ,
	//the id of power user will be added to a coloum of Pid in admin table
	//this acts a foriegn key
	//	targetEntity = Admin.class
	

}
