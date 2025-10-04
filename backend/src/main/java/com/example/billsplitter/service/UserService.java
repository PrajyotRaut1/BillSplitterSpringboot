package com.example.billsplitter.service;


import com.example.billsplitter.dto.RegisterRequest;
import com.example.billsplitter.dto.UserDTO;
import com.example.billsplitter.model.User;


import java.util.Optional;


public interface UserService {
UserDTO register(RegisterRequest request);
Optional<User> findById(Long id);
Optional<User> findByEmail(String email);
}