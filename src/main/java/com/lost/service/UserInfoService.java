package com.lost.service;

import com.lost.entity.UserInfo;

import java.util.List;
import java.util.Map;

public interface UserInfoService {

    List<UserInfo> findAll();

    UserInfo findById(Integer id);

    void insert(Map<String, Object> params);

    Boolean hasExist(String username);

    UserInfo getByUsername(String username);

    void setPassword(String username, String password);

    void updateUserInfo(UserInfo userInfo);
}
