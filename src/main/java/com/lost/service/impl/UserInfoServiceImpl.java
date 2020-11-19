package com.lost.service.impl;

import com.lost.entity.UserInfo;
import com.lost.mapper.UserInfoMapper;
import com.lost.service.UserInfoService;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    UserInfoMapper userInfoMapper;


    @Override
    public List<UserInfo> findAll() {
        return userInfoMapper.findAll();
    }


    @Override
    public void insert(Map<String, Object> params){
        UserInfo userInfo = new UserInfo();
        userInfo.setUserName(params.get("username").toString());
        userInfo.setPassword(params.get("password").toString());

        if (params.get("nickname") != null){
            userInfo.setNickname(params.get("nickname").toString());
        }
        if (params.get("sex") != null){
            userInfo.setSex(params.get("sex").toString());
        }
        if (params.get("telephone") != null){
            userInfo.setTelephone(params.get("telephone").toString());
        }
        if (params.get("signature") != null){
            userInfo.setSignature(params.get("signature").toString());
        }

        //MD5加密
        Md5Hash md5Hash = new Md5Hash(userInfo.getPassword());
        userInfo.setPassword(md5Hash.toHex());

        userInfoMapper.insertSelective(userInfo);
    }

    @Override
    public Boolean hasExist(String username){
        return userInfoMapper.getByUsername(username) != null;
    }

}
