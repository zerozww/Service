package com.lost.shiro;

import com.lost.entity.UserInfo;
import com.lost.mapper.UserInfoMapper;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;

public class CustomRealm extends AuthorizingRealm {
    @Autowired
    UserInfoMapper userInfoMapper;


    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        String principal = (String)token.getPrincipal();
        UserInfo userInfo = userInfoMapper.getByUsername(principal);

        if (!ObjectUtils.isEmpty(userInfo)){
            return new SimpleAuthenticationInfo(userInfo.getUserName(), userInfo.getPassword(), this.getName());
        }

        return null;
    }
}
