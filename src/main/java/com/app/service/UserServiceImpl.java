package com.app.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.dto.UserDto;
import com.app.enums.UserRole;
import com.app.exception.ResourceNotFoundException;
import com.app.pojos.Role;
import com.app.pojos.UserEntity;
import com.app.repository.RoleRepository;
import com.app.repository.UserRepository;

@Service
public class UserServiceImpl implements IUserService {
	@Autowired
    private UserRepository userRepo;
	@Autowired
	private RoleRepository roleRepo;
	@Autowired
	private PasswordEncoder encoder;

    @Autowired
    private ModelMapper modelMapper;
     //User registration
    @Override
    public UserDto saveUser(UserDto userDTO) {
        UserEntity user = modelMapper.map(userDTO, UserEntity.class);
        user.setActive(true);
        user.setPassword(encoder.encode(user.getPassword()));
        UserEntity savedUser = userRepo.save(user);
        return modelMapper.map(savedUser, UserDto.class);
    }

    @Override
    public Role addRole(Role role) {
        return roleRepo.save(role);
    }

    @Override
    public String linkUserRole(String userName, UserRole roleName) {
        UserEntity user = userRepo.findByUserName(userName)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Role userRole = roleRepo.findByRole(roleName)
                .orElseThrow(() -> new RuntimeException("Role not found"));
        user.getRoles().add(userRole);
        userRepo.save(user);  // save the updated user entity
        return "Linked role to user";
    }

		
	

	

//    @Override
//    public UserDto createUser(UserDto userDto) {
//        // Map UserDto to User entity
//        UserEntity user = modelMapper.map(userDto, UserEntity.class);
//        
//        // Save User entity to the repository
//        UserEntity savedUser = userRepository.save(user);
//        
//        // Map saved User entity back to UserDto
//        return modelMapper.map(savedUser, UserDto.class);
//    }

    @Override
    public UserDto getUserById(Long id) {
        // Find User by ID or throw ResourceNotFoundException if not found
        UserEntity user = userRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User id "+ id));
        
        // Map User entity to UserDto
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public UserDto updateUser(Long id, UserDto userDto) {
        // Find User by ID or throw ResourceNotFoundException if not found
        UserEntity user = userRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User id "+ id));
        
        // Update the existing User entity with new data from UserDto
        modelMapper.map(userDto, user);
        
        // Save the updated User entity to the repository
        UserEntity updatedUser = userRepo.save(user);
        
        // Map updated User entity back to UserDto
        return modelMapper.map(updatedUser, UserDto.class);
    }

    @Override
    public void deleteUser(Long id) {
        // Find User by ID or throw ResourceNotFoundException if not found
        UserEntity user = userRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User id "+ id));
        
        // Delete the User entity from the repository
        userRepo.delete(user);
    }
}
