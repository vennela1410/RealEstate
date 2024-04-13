package com.softwareverification.realestate.service;

import com.softwareverification.realestate.entity.UserEntity;

public interface UserService {

    public String registerUser(UserEntity user, boolean userId);

    public String loginUser(UserEntity user);
}
