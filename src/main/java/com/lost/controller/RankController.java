package com.lost.controller;

import com.lost.entity.Rank;
import com.lost.entity.UserInfo;
import com.lost.service.RankService;
import com.lost.service.UserInfoService;
import com.lost.utils.response.ResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RankController {
    @Autowired
    RankService rankService;

    @Autowired
    UserInfoService userInfoService;

    @RequestMapping("/rank")
    public ResponseWrapper getAllRank(){
        ResponseWrapper response = null;
        List<Rank> rankList = rankService.findAll();

        for (Rank rank : rankList){
            UserInfo userInfo = userInfoService.findById(rank.getUserId());
            rank.setNickName(userInfo.getNickname());
        }

        response = ResponseWrapper.markSuccess();
        response.setExtra("rankList", rankList);

        return response;
    }


}
