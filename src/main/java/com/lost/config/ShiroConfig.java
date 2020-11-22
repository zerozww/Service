package com.lost.config;

import com.lost.shiro.CustomRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * 用来整合shiro框架相关的配置类
 */
@Configuration
public class ShiroConfig {
    //创建shiroFilter
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        //给filter设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);

        //配置受限资源和公共资源
        Map<String, String> map = new HashMap<>();
        map.put("/user/login", "anon"); //anon，设为公共资源
        map.put("/user/register", "anon");
        map.put("/user/unAuthc", "anon");
        map.put("/**", "authc");

        //未认证，则访问/user/unAuthc，会返回“无权限”的提示
        shiroFilterFactoryBean.setLoginUrl("/user/unAuthc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);

        return shiroFilterFactoryBean;
    }

    //创建安全管理器
    @Bean
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("getRealm") Realm realm){
        DefaultWebSecurityManager webSecurityManager = new DefaultWebSecurityManager();

        //给安全管理器设置Realm
        webSecurityManager.setRealm(realm);

        return webSecurityManager;
    }

    //创建自定义Realm
    @Bean
    public Realm getRealm(){
        CustomRealm customRealm = new CustomRealm();
        //修改凭证校验匹配器
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        credentialsMatcher.setHashAlgorithmName("MD5");
        customRealm.setCredentialsMatcher(credentialsMatcher);

        return customRealm;
    }

}
