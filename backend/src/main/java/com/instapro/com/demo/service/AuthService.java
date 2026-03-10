package com.instapro.com.demo.service;

import com.instapro.com.demo.entity.User;

public interface AuthService {
    String signup(User user);
    String login(String username, String password);
}
