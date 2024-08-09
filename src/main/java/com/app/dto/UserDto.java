package com.app.dto;

import java.util.Set;

import com.app.pojos.Role;

import lombok.Data;
@Data
public class UserDto {
	 private Long id;
	    private String userName;
	   private String password;
	   private boolean active;
	    private Set<Role> roles;
}