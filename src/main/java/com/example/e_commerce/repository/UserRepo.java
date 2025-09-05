package com.example.e_commerce.repository;

import com.example.e_commerce.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Integer> {

    Optional<User> findByUserName(String userName);

    Optional<User> findById(int userId);

    Optional<User> findByEmail(String email);

    boolean existsByUserNameAndUserIdNot(String userName, int userId);

    boolean existsByEmailAndUserIdNot(String email, int userId);
}
