package com.app.service;

import com.app.dto.UserDto;
import com.app.enums.UserRole;
import com.app.pojos.Role;
import com.app.pojos.UserEntity;

public interface IUserService {
    //UserDto createUser(UserDto userDto);
    UserDto getUserById(Long id);
    UserDto updateUser(Long id, UserDto userDto);
    void deleteUser(Long id);
    UserDto saveUser(UserDto user);
    Role addRole(Role role);
    String linkUserRole(String userName,UserRole roleName);
    
}