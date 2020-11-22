package com.lost.controller;

import com.lost.service.DictService;
import com.lost.utils.response.ResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dict")
public class DictController {
    @Autowired
    DictService dictService;

    /**
     * 获取类别字典列表
     * @return
     */
    @RequestMapping("/get-categorylist")
    public ResponseWrapper getCategoryList(){
        ResponseWrapper response = null;

        response = ResponseWrapper.markSuccess();
        response.setExtra("categoryList", dictService.findByCodeType("category"));
        return response;
    }

}
