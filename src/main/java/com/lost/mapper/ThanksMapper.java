package com.lost.mapper;

import com.lost.entity.Thanks;

public interface ThanksMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Thanks record);

    int insertSelective(Thanks record);

    Thanks selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Thanks record);

    int updateByPrimaryKey(Thanks record);
}