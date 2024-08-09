package com.app.repository;

import com.app.enums.UserRole;
import com.app.pojos.Role;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long>{
	Optional<Role>findByRole(UserRole role);

}
