package com.jcom.user.service;

import com.jcom.user.model.User;

import java.util.List;

public interface UserService {

    public User save(User user);

    User findByUsername(String username);

    List<User> findUsers(List<Long> idList);
}
