package com.lost.controller;

import com.lost.entity.Thanks;
import com.lost.entity.UserInfo;
import com.lost.service.ThanksService;
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
@RequestMapping("/thanks")
public class ThanksController {
    @Autowired
    ThanksService thanksService;

    @Autowired
    UserInfoService userInfoService;

    @RequestMapping("/get-note")
    public ResponseWrapper getNote(){
        ResponseWrapper response = null;

        List<Thanks> list = thanksService.findAll();
        response = ResponseWrapper.markSuccess();
        response.setExtra("noteList", list);

        return response;
    }

    @RequestMapping("/post-note")
    public ResponseWrapper postNote(@RequestParam Map<String, Object> params){
        ResponseWrapper response = null;

        if (params.get("title") == null || params.get("content") == null || params.get("title").toString() == "" || params.get("content").toString() == ""){
            response = ResponseWrapper.markParamError();
            return response;
        }

        String title = params.get("title").toString();
        String content = params.get("content").toString();
        UserInfo userInfo = userInfoService.getByUsername((String) SecurityUtils.getSubject().getPrincipal());
        thanksService.insert(title, content, userInfo);
        response = ResponseWrapper.markSuccess();

        return response;
    }



}
