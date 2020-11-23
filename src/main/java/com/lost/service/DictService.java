package com.lost.service;

import com.lost.entity.Dictionary;

import java.util.List;

public interface DictService {
    List<Dictionary> findByCodeType(String codeType);

    Dictionary getByCodeAndType(String code, String codeType);
}
