package com.lost.utils;

import com.lost.entity.UserInfo;
import org.apache.shiro.SecurityUtils;

public class UserUtil {

    /**
     * 获取当前用户
     * @return
     */
    public static UserInfo getUser(){
        return (UserInfo) SecurityUtils.getSubject().getPrincipal();
    }

    /**
     * 获取当前用户ID
     * @return
     */
    public static Integer getId(){
        UserInfo userInfo = (UserInfo) SecurityUtils.getSubject().getPrincipal();
        return userInfo.getId();
    }

    /**
     * 获取当前用户名
     * @return
     */
    public static String getUsername(){
        UserInfo userInfo = (UserInfo) SecurityUtils.getSubject().getPrincipal();
        return userInfo.getUserName();
    }

    /**
     * 获取用户昵称
     * @return
     */
    public static String getNickname(){
        UserInfo userInfo = (UserInfo) SecurityUtils.getSubject().getPrincipal();
        return userInfo.getNickname();
    }

    /**
     * 获取用户性别
     * @return
     */
    public static String getSex(){
        UserInfo userInfo = (UserInfo) SecurityUtils.getSubject().getPrincipal();
        return userInfo.getSex();
    }

    /**
     * 获取用户联系方式
     * @return
     */
    public static String getTelephone(){
        UserInfo userInfo = (UserInfo) SecurityUtils.getSubject().getPrincipal();
        return userInfo.getTelephone();
    }

    /**
     * 获取用户个性签名
     * @return
     */
    public static String getSignature(){
        UserInfo userInfo = (UserInfo) SecurityUtils.getSubject().getPrincipal();
        return userInfo.getSignature();
    }

    /**
     * 获取用户头像路径
     * @return
     */
    public static String getIcon(){
        UserInfo userInfo = (UserInfo) SecurityUtils.getSubject().getPrincipal();
        return userInfo.getIcon();
    }


}
