package com.lost.config;

import org.apache.shiro.realm.Realm;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

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

        return shiroFilterFactoryBean;
    }

    //创建安全管理器
    @Bean
    public DefaultWebSecurityManager getDefaultWebSecurityManager(SimpleAccountRealm SimpleAccountRealm){
        DefaultWebSecurityManager webSecurityManager = new DefaultWebSecurityManager();

        //给安全管理器设置Realm
        webSecurityManager.setRealm(SimpleAccountRealm);

        return webSecurityManager;
    }

    //创建自定义Realm
    @Bean
    public SimpleAccountRealm getRealm(){
        SimpleAccountRealm SimpleAccountRealm = new SimpleAccountRealm();
        return SimpleAccountRealm;
    }

}
