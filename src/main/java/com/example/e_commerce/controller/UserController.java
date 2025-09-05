package com.example.e_commerce.controller;

import com.example.e_commerce.dto.UserDto;
import com.example.e_commerce.dto.UserUpdateDto;
import com.example.e_commerce.entity.User;
import com.example.e_commerce.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/create")
    public UserDto createUsers(@RequestBody UserDto userDto) {
        return userService.createUser(userDto);
    }

    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.getAllUser();
    }

    @GetMapping("/{id}")
    public UserDto getUsersById(@PathVariable("id") int id) {
        return userService.getUserById(id);
    }
    
    @PutMapping("/update/{id}")
    public UserDto updateUsers(@PathVariable("id") int id, @RequestBody UserUpdateDto userUpdateDto) {
        return userService.updateUser(id, userUpdateDto);
    }
}
