package com.lost.mapper;

import com.lost.entity.Rank;

import java.util.List;

public interface RankMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Rank record);

    int insertSelective(Rank record);

    Rank selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Rank record);

    int updateByPrimaryKey(Rank record);

    List<Rank> findAll();

    Rank findByUserId(Integer userId);
}