package com.example.billsplitter.controller;


import com.example.billsplitter.dto.UserDTO;
import com.example.billsplitter.model.User;
import com.example.billsplitter.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
private final UserService userService;


@GetMapping("/{id}")
public ResponseEntity<UserDTO> getUser(@PathVariable Long id) {
User u = userService.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found"));
UserDTO dto = new UserDTO();
dto.setId(u.getId());
dto.setName(u.getName());
dto.setEmail(u.getEmail());
return ResponseEntity.ok(dto);
}
}