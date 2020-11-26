package com.lost.service;

import com.lost.entity.LostProperty;
import com.lost.entity.UserInfo;

import java.util.List;

public interface LostService {

    LostProperty findLostById(Integer lostId);

    List<LostProperty> findLostList(UserInfo userInfo);

    void insert(Integer userId,String title,String category,String detail,String xAxis,String yAxis);

    List<LostProperty> findUnfinishedLostList();

    void confirm(Integer lostId);
}
