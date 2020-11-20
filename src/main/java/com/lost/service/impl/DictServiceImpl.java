package com.lost.service.impl;

import com.lost.entity.Dictionary;
import com.lost.mapper.DictionaryMapper;
import com.lost.service.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DictServiceImpl implements DictService {
    @Autowired
    DictionaryMapper dictionaryMapper;

    @Override
    public List<Dictionary> findByCodeType(String codeType){
        return dictionaryMapper.findByTypeCode(codeType);
    }


}
