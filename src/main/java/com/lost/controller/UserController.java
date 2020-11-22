package com.lost.controller;

import com.lost.entity.UserInfo;
import com.lost.service.UserInfoService;
import com.lost.utils.FileUtil;
import com.lost.utils.response.ResponseWrapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserInfoService userInfoService;


    /**
     * 登录方法
     * @param params
     * @return
     */
    @RequestMapping("/login")
    public ResponseWrapper login(@RequestParam Map<String, Object> params){
        ResponseWrapper response = null;

        if (params.get("username") == null || params.get("password") == null || params.get("username").toString() == "" || params.get("password").toString() == ""){
            response = ResponseWrapper.markDefault(411, "用户名或密码不能为空");
            return response;
        }

        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(new UsernamePasswordToken(params.get("username").toString(), params.get("password").toString()));
            response = ResponseWrapper.markSuccess();
            return response;
        } catch (UnknownAccountException e) {
            response = ResponseWrapper.markDefault(412, "用户名不存在！");
            return response;
        } catch (IncorrectCredentialsException e) {
            response = ResponseWrapper.markDefault(413, "密码错误！");
            return response;
        }
    }

    /**
     * 退出登录
     * @return
     */
    @RequestMapping("/logout")
    public ResponseWrapper logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return ResponseWrapper.markSuccess();
    }

    /**
     * 注册方法
     * @param params
     * @return
     */
    @RequestMapping("/register")
    public ResponseWrapper register(@RequestParam Map<String, Object> params){
        ResponseWrapper response = null;
        if (params.get("username") == null || params.get("password") == null || params.get("username").toString() == "" || params.get("password").toString() == ""){
            response = ResponseWrapper.markDefault(421, "用户名或密码不能为空");
            return response;
        }

        String username = params.get("username").toString();
        if (userInfoService.hasExist(username)){
            response = ResponseWrapper.markDefault(422, "用户已存在");
            return response;
        }
        userInfoService.insert(params);

        response = ResponseWrapper.markSuccess();
        return response;
    }

    /**
     * 无权限时，会跳转到此方法，返回“没有该接口的访问权限”的信息
     * @return
     */
    @RequestMapping("/unAuthc")
    public ResponseWrapper unAuthc(){
        ResponseWrapper response = null;

        response = ResponseWrapper.markApiNotPermission();
        return response;
    }

    /**
     * 获取个人详细信息
     * @return
     */
    @RequestMapping("/get-userinfo")
    public ResponseWrapper getUserInfo(){
        ResponseWrapper response = null;

        String username = (String) SecurityUtils.getSubject().getPrincipal();
        response = ResponseWrapper.markSuccess();
        response.setExtra("user", userInfoService.getByUsername(username));
        return response;
    }

    /**
     * 更改密码
     * @param params
     * @return
     */
    @RequestMapping("/set-password")
    public ResponseWrapper setPassword(@RequestParam Map<String, Object> params){
        ResponseWrapper response = null;

        String username = (String) SecurityUtils.getSubject().getPrincipal();
        userInfoService.setPassword(username, params.get("password").toString());
        response = ResponseWrapper.markSuccess();
        return response;
    }

    /**
     * 更新用户信息
     * @param params
     * @return
     */
    @RequestMapping("/set-userinfo")
    public ResponseWrapper setUserinfo(@RequestParam Map<String, Object> params){
        ResponseWrapper response = null;

        String username = (String) SecurityUtils.getSubject().getPrincipal();
        UserInfo userInfo = userInfoService.getByUsername(username);
        userInfo.setNickname(params.get("nickname").toString());
        userInfo.setSex(params.get("sex").toString());
        userInfo.setTelephone(params.get("telephone").toString());
        userInfo.setSignature(params.get("signature").toString());
        userInfoService.updateUserInfo(userInfo);

        response = ResponseWrapper.markSuccess();
        return response;
    }

    @RequestMapping("/picture")
    public ResponseWrapper uploadImg(@RequestParam("icon") MultipartFile file) {
        ResponseWrapper response = null;

        String username = (String) SecurityUtils.getSubject().getPrincipal();
        UserInfo userInfo = userInfoService.getByUsername(username);

        String fileName = file.getOriginalFilename();
        //设置文件上传路径
        String filePath = getUploadPath();
        try {
            String fullPathName = FileUtil.uploadFile(file.getBytes(), filePath, fileName);
            userInfo.setIcon(fullPathName);
            userInfoService.updateUserInfo(userInfo);
            response = ResponseWrapper.markSuccess();
            return response;
        } catch (Exception e) {
            response = ResponseWrapper.markError();
            return response;
        }
    }

    private String getUploadPath() {
        File path = null;
        try {
            path = new File(ResourceUtils.getURL("classpath:").getPath());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (!path.exists()) path = new File("");
        File upload = new File(path.getAbsolutePath(), "static/picture/");
        if (!upload.exists()) upload.mkdirs();
        return upload.getAbsolutePath();
    }
}
