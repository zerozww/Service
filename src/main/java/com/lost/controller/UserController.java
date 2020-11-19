package com.lost.controller;

import com.lost.entity.UserInfo;
import com.lost.mapper.UserInfoMapper;
import com.lost.service.UserInfoService;
import com.lost.utils.response.ResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserInfoService userInfoService;

    @RequestMapping("/hello")
    public ResponseWrapper hello(){
        ResponseWrapper response = null;
        List<UserInfo> userList = userInfoService.findAll();

        response = ResponseWrapper.markSuccess();
        response.setExtra("userList", userList);
        return response;
    }

    @RequestMapping("/login")
    public ResponseWrapper login(@RequestParam Map<String, Object> params){
        ResponseWrapper response = null;
        List<UserInfo> userList = userInfoService.findAll();

        response = ResponseWrapper.markSuccess();
        response.setExtra("userList", userList);
        return response;
    }

}
