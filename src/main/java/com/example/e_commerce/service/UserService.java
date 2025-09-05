package com.example.e_commerce.service;

import com.example.e_commerce.dto.UserDto;
import com.example.e_commerce.dto.UserUpdateDto;
import com.example.e_commerce.entity.User;
import com.example.e_commerce.exception.*;
import com.example.e_commerce.repository.UserRepo;
import com.example.e_commerce.util.CustomUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepo userRepo;
    private final CustomUtils customUtils;
    //private final PasswordEncoder passwordEncoder;

    public UserDto createUser(UserDto userDto) {
        if (userRepo.existsById(userDto.getUserId())) {
            throw new UserIdAlreadyExistException("User id already exist.");
        }
        if (userRepo.findByUserName(userDto.getUserName()).isPresent()) {
            throw new UserNameAlreadyExistException("Username already taken.");
        }
        userDto.setRole(User.Role.CUSTOMER);
        userDto.setPassword(userDto.getPassword());
        if (userRepo.findByEmail(userDto.getEmail()).isPresent()) {
            throw new EmailAlreadyExistException("Email already taken.");
        }
        return customUtils.toUserDto(userRepo.save(customUtils.toUserEntity(userDto)));
    }

    public List<UserDto> getAllUser() {
        return userRepo.findAll().stream().map(customUtils::toUserDto).collect(Collectors.toList());
    }

    public UserDto getUserById(int id) {
        return customUtils.toUserDto(userRepo.findById(id).orElseThrow(() -> new UserIdNotFoundException("No user-id " + id)));
    }

    public UserDto updateUser(int id, UserUpdateDto userUpdateDto) {
        User user = userRepo.findById(id).orElseThrow(() -> new UserIdNotFoundException("No user-id " + id));
        if (userUpdateDto.getUserName() != null && !userUpdateDto.getUserName().isEmpty()) {
            if (userRepo.existsByUserNameAndUserIdNot(userUpdateDto.getUserName(), id)) {
               throw new UserNameAlreadyExistException("Username already exists.");
            }
            user.setUserName(userUpdateDto.getUserName());
        }
        if (userUpdateDto.getPassword() != null && !userUpdateDto.getPassword().isEmpty()) {
            user.setPassword(userUpdateDto.getPassword());
        }
        if (userUpdateDto.getEmail() != null && !userUpdateDto.getEmail().isEmpty()) {
            if (userRepo.existsByEmailAndUserIdNot(userUpdateDto.getEmail(), id)) {
                throw new EmailAlreadyExistException("Email already taken.");
            }
            user.setEmail(userUpdateDto.getEmail());
        }
        User updatedUser = userRepo.save(user);
        return new UserDto(
                updatedUser.getUserId(),
                updatedUser.getUserName(),
                updatedUser.getPassword(),
                updatedUser.getEmail(),
                updatedUser.getRole()
        );
//        return customUtils.toUserDto(userRepo.save(user));
    }
}
