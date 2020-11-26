package com.lost.mapper;

import com.lost.entity.LostProperty;
import com.lost.entity.UserInfo;

import java.util.List;

public interface LostPropertyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LostProperty record);

    int insertSelective(LostProperty record);

    LostProperty selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LostProperty record);

    int updateByPrimaryKey(LostProperty record);

    List<LostProperty> findAll();

    List<LostProperty> findAllUnfinishedLost();

    List<LostProperty> getLostByUserId(Integer userId);
}