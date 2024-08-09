package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.UserDto;
import com.app.enums.UserRole;
import com.app.pojos.Role;
import com.app.service.IUserService;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private IUserService userService;

//    @PostMapping
//    public UserDto createUser(@RequestBody UserDto userDto) {
//        return userService.createUser(userDto);
//    }
    @PostMapping
    public ResponseEntity<UserDto> saveUser(@RequestBody UserDto userDTO) {
        UserDto savedUser = userService.saveUser(userDTO);
        return ResponseEntity.ok(savedUser);
    }

    @PostMapping("/roles")
    public ResponseEntity<String> addRole(@RequestBody Role role) {
        userService.addRole(role);
        return ResponseEntity.ok("Role added");
    }

    @PostMapping("/linkRole")
    public ResponseEntity<String> linkUserRole(@RequestParam String userName, @RequestParam UserRole roleName) {
        String response = userService.linkUserRole(userName, roleName);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PutMapping("/{id}")
    public UserDto updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {
        return userService.updateUser(id, userDto);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}