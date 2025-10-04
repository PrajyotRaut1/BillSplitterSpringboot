package com.example.billsplitter.controller;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.billsplitter.service.FriendService;
import com.example.billsplitter.service.UserService;
import com.example.billsplitter.model.Friend;
import com.example.billsplitter.model.User;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/friends")
@RequiredArgsConstructor
public class FriendController {
private final FriendService friendService;
private final UserService userService;


// For simplicity, client passes currentUserId and friendId in these endpoints.


@PostMapping
public ResponseEntity<?> addFriend(@RequestParam Long userId, @RequestParam Long friendId) {
User u = userService.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));
User f = userService.findById(friendId).orElseThrow(() -> new IllegalArgumentException("Friend not found"));
Friend saved = friendService.addFriend(u, f);
return ResponseEntity.ok(saved);
}


@DeleteMapping
public ResponseEntity<?> removeFriend(@RequestParam Long userId, @RequestParam Long friendId) {
User u = userService.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));
User f = userService.findById(friendId).orElseThrow(() -> new IllegalArgumentException("Friend not found"));
friendService.removeFriend(u, f);
return ResponseEntity.ok().build();
}


@GetMapping("/{id}")
public ResponseEntity<?> listFriends(@RequestParam Long userId) {
User u = userService.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));
List<Friend> list = friendService.listFriends(u);
List<?> res = list.stream().map(fr -> {
return java.util.Map.of("id", fr.getFriend().getId(), "name", fr.getFriend().getName(), "email", fr.getFriend().getEmail());
}).collect(Collectors.toList());
return ResponseEntity.ok(res);
}
}