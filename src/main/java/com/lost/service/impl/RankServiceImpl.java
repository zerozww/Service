package com.lost.service.impl;

import com.lost.entity.Rank;
import com.lost.mapper.RankMapper;
import com.lost.service.RankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RankServiceImpl implements RankService {
    @Autowired
    RankMapper rankMapper;

    @Override
    public List<Rank> findAll(){
        return rankMapper.findAll();
    }

    /**
     * 当用户标记失物招领信息完成后，执行该方法，若没有该用户的信息，则新增一条，若有，则count加1
     * @param userId
     */
    @Override
    public void addRankByUserId(Integer userId){
        Rank rank = rankMapper.findByUserId(userId);

        if (rank != null){
            rank.setCount(rank.getCount() + 1);
            rankMapper.updateByPrimaryKeySelective(rank);
        } else {
            rank = new Rank();
            rank.setUserId(userId);
            rank.setCount(1);
            rankMapper.insert(rank);
        }

    }

}
