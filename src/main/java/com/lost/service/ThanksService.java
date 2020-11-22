package com.lost.service;

import com.lost.entity.Thanks;
import com.lost.entity.UserInfo;

import java.util.List;

public interface ThanksService {
    void insert(String title, String content, UserInfo userInfo);

    List<Thanks> findAll();
}
