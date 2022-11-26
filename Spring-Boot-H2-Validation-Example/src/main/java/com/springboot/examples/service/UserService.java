package com.springboot.examples.service;

import com.springboot.examples.model.UserInfo;


public interface UserService {
    public int createUser(UserInfo userInfo);
    public UserInfo fetchUser(int id);
}
