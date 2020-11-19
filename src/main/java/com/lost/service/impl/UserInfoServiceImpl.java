package com.lost.service.impl;

import com.lost.entity.UserInfo;
import com.lost.mapper.UserInfoMapper;
import com.lost.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    UserInfoMapper userInfoMapper;


    @Override
    public List<UserInfo> findAll() {
        return userInfoMapper.findAll();
    }
}
