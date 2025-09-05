package com.example.e_commerce.dto;

import com.example.e_commerce.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private int userId;
    private String userName;
    private String password;
    private String email;
    private User.Role role;
}
