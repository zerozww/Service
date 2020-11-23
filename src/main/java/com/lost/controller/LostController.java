package com.lost.controller;


import com.lost.entity.Dictionary;
import com.lost.entity.LostProperty;
import com.lost.entity.UserInfo;
import com.lost.service.DictService;
import com.lost.service.LostService;
import com.lost.service.UserInfoService;
import com.lost.utils.response.ResponseWrapper;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/lost")
public class LostController {

    @Autowired
    LostService lostService;

    @Autowired
    UserInfoService userInfoService;

    @Autowired
    DictService dictService;

    @RequestMapping("/get-lostdetail")
    public ResponseWrapper getLostDetail(@RequestParam Map<String, Object> params)
    {
        ResponseWrapper response = null;

        String lostid = params.get("lostId").toString();
        Integer lostId = Integer.parseInt(lostid);
        LostProperty lostProperty = lostService.findLostById(lostId);

        response = ResponseWrapper.markSuccess();
        response.setExtra("lost", lostProperty);

        return response;
    }

    @RequestMapping("/get-lostlist")
    public ResponseWrapper getLostList()
    {
        ResponseWrapper response = null;

        List<LostProperty> lostList = lostService.findLostList();
        for (LostProperty lostProperty : lostList) {
            Dictionary dict = dictService.getByCodeAndType(lostProperty.getCategory(), "category");
            lostProperty.setCategory(dict.getDictName());
        }

        response = ResponseWrapper.markSuccess();
        response.setExtra("lostList", lostList);

        return response;
    }

    @RequestMapping("/post-lost")
    public ResponseWrapper postLost(@RequestParam Map<String, Object> params)
    {
        ResponseWrapper response = null;

        String username = (String) SecurityUtils.getSubject().getPrincipal();
        UserInfo userInfo = userInfoService.getByUsername(username);
        Integer userId = userInfo.getId();
        String title = params.get("title").toString();
        String category = params.get("category").toString();
        String detail = params.get("detail").toString();
        String xAxis = params.get("xAxis").toString();
        String yAxis = params.get("yAxis").toString();
        lostService.insert(userId,title,category,detail,xAxis,yAxis);

        response = ResponseWrapper.markSuccess();
        return response;
    }

    @RequestMapping("/get-allvalidlost")
    public ResponseWrapper getAllValidLost()
    {
        ResponseWrapper response = null;

        List<LostProperty> list = lostService.findUnfinishedLostList();

        response = ResponseWrapper.markSuccess();
        response.setExtra("lostList", list);

        return response;
    }

    @RequestMapping("/confirm")
    public ResponseWrapper confirm(@RequestParam Map<String, Object> params)
    {
        ResponseWrapper response = null;

        Integer lostId = Integer.parseInt(params.get("lostId").toString());
        lostService.confirm(lostId);

        response = ResponseWrapper.markSuccess();
        return response;
    }

}
