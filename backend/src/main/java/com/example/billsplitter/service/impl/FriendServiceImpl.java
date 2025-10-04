package com.example.billsplitter.service.impl;


import com.example.billsplitter.model.Friend;
import com.example.billsplitter.model.User;
import com.example.billsplitter.repository.FriendRepository;
import com.example.billsplitter.service.FriendService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;


@Service
@RequiredArgsConstructor
public class FriendServiceImpl implements FriendService {
private final FriendRepository friendRepository;


@Override
public Friend addFriend(User user, User friend) {
// Avoid duplicates
if (friendRepository.findByUserAndFriend(user, friend).isPresent()) {
throw new IllegalArgumentException("Already friends");
}
Friend f = Friend.builder().user(user).friend(friend).build();
return friendRepository.save(f);
}


@Override
public void removeFriend(User user, User friend) {
friendRepository.findByUserAndFriend(user, friend).ifPresent(friendRepository::delete);
}


@Override
public List<Friend> listFriends(User user) {
return friendRepository.findByUser(user);
}
}