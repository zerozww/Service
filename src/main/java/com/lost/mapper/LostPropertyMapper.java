package com.lost.mapper;

import com.lost.entity.LostProperty;

public interface LostPropertyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LostProperty record);

    int insertSelective(LostProperty record);

    LostProperty selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LostProperty record);

    int updateByPrimaryKey(LostProperty record);
}