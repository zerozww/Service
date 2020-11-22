package com.lost.service;

import com.lost.entity.Rank;

import java.util.List;

public interface RankService {
    List<Rank> findAll();

    void addRankByUserId(Integer userId);
}
