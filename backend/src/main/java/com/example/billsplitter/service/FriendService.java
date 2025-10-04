package com.example.billsplitter.service;

import com.example.billsplitter.model.Friend;
import com.example.billsplitter.model.User;

import java.util.List;

public interface FriendService {
    Friend addFriend(User user, User friend);
    void removeFriend(User user, User friend);
    List<Friend> listFriends(User user);
}