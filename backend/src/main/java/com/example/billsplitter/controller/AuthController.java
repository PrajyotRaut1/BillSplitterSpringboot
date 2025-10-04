package com.example.billsplitter.controller;


import com.example.billsplitter.dto.LoginRequest;
import com.example.billsplitter.dto.RegisterRequest;
import com.example.billsplitter.dto.UserDTO;
import com.example.billsplitter.model.User;
import com.example.billsplitter.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
private final UserService userService;
private final PasswordEncoder passwordEncoder;


@PostMapping("/register")
public ResponseEntity<UserDTO> register(@Valid @RequestBody RegisterRequest req) {
UserDTO dto = userService.register(req);
return ResponseEntity.ok(dto);
}


@PostMapping("/login")
public ResponseEntity<?> login(@Valid @RequestBody LoginRequest req) {
// Very simple login: validate credentials and return user DTO. Replace with JWT for production.
User u = userService.findByEmail(req.getEmail()).orElseThrow(() -> new IllegalArgumentException("Invalid credentials"));
if (!passwordEncoder.matches(req.getPassword(), u.getPassword())) {
throw new IllegalArgumentException("Invalid credentials");
}
UserDTO dto = new UserDTO();
dto.setId(u.getId());
dto.setName(u.getName());
dto.setEmail(u.getEmail());
return ResponseEntity.ok(dto);
}
}