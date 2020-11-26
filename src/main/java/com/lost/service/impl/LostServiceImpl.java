package com.lost.service.impl;


import com.lost.entity.LostProperty;
import com.lost.entity.Thanks;
import com.lost.entity.UserInfo;
import com.lost.mapper.LostPropertyMapper;
import com.lost.mapper.UserInfoMapper;
import com.lost.service.LostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LostServiceImpl implements LostService {
    @Autowired
    LostPropertyMapper lostPropertyMapper;

    @Autowired
    UserInfoMapper userInfoMapper;

    @Override
    public LostProperty findLostById(Integer lostId)
    {
        LostProperty lostProperty = lostPropertyMapper.selectByPrimaryKey(lostId);
        lostProperty.setNickName(userInfoMapper.selectByPrimaryKey(lostProperty.getUserId()).getNickname());
        return lostProperty;
    }

    @Override
    public List<LostProperty> findLostList(UserInfo userInfo)
    {
        List<LostProperty> lostList = lostPropertyMapper.getLostByUserId(userInfo.getId());

        for (LostProperty lostProperty : lostList){
            lostProperty.setNickName(userInfoMapper.selectByPrimaryKey(lostProperty.getUserId()).getNickname());
        }

        return lostList;
    }

    @Override
    public void insert(Integer userId,String title,String category,String detail,String xAxis,String yAxis)
    {
        LostProperty lostProperty = new LostProperty();
        lostProperty.setUserId(userId);
        lostProperty.setTitle(title);
        lostProperty.setCategory(category);
        lostProperty.setDetail(detail);
        lostProperty.setxAxis(xAxis);
        lostProperty.setyAxis(yAxis);
        lostProperty.setTag("0");
        lostPropertyMapper.insertSelective(lostProperty);
    }

    @Override
    public List<LostProperty> findUnfinishedLostList()
    {
        List<LostProperty> list = lostPropertyMapper.findAllUnfinishedLost();

        for (LostProperty lostProperty : list){
            lostProperty.setNickName(userInfoMapper.selectByPrimaryKey(lostProperty.getUserId()).getNickname());
        }

        return list;
    }

    @Override
    public void confirm(Integer lostId)
    {
        LostProperty lostProperty = lostPropertyMapper.selectByPrimaryKey(lostId);
        lostProperty.setTag("1");
        lostPropertyMapper.updateByPrimaryKey(lostProperty);
    }
}
