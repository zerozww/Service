package com.lost.service.impl;

import com.lost.entity.Thanks;
import com.lost.entity.UserInfo;
import com.lost.mapper.ThanksMapper;
import com.lost.mapper.UserInfoMapper;
import com.lost.service.ThanksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ThanksServiceImpl implements ThanksService {
    @Autowired
    ThanksMapper thanksMapper;

    @Autowired
    UserInfoMapper userInfoMapper;

    @Override
    public void insert(String title, String content, UserInfo userInfo){
        Thanks thanks = new Thanks();
        thanks.setTitle(title);
        thanks.setContent(content);
        thanks.setUserId(userInfo.getId());
        thanks.setCreateTime(new Date());
        thanksMapper.insert(thanks);
    }

    @Override
    public List<Thanks> findAll(){
        List<Thanks> list = thanksMapper.findAll();

        for (Thanks thanks : list){
            thanks.setNickName(userInfoMapper.selectByPrimaryKey(thanks.getUserId()).getNickname());
        }

        return list;
    }


}
