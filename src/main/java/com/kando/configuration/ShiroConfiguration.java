package com.kando.configuration;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.util.ThreadContext;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.filter.DelegatingFilterProxy;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Shiro配置
 *
 *
 */
@Configuration
public class ShiroConfiguration {

    @Bean("securityManager")
    public SecurityManager securityManager(MyRealm oAuth2Realm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(oAuth2Realm);
        return securityManager;
    }

    @Bean("shiroFilter")
    public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager,StringRedisTemplate stringRedisTemplate) {
        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        HashMap<String, Filter> filters = new HashMap<>();
        filters.put("token",new MyShiroFilter(stringRedisTemplate));
        shiroFilter.setFilters(filters);
        shiroFilter.setSecurityManager(securityManager);
        Map<String, String> filterMap = new LinkedHashMap<>();
        filterMap.put("/", "anon");
        filterMap.put("/role/roleAll", "anon");
        filterMap.put("/user/image","anon");
        filterMap.put("/user/loginByPwd","anon");
        filterMap.put("/user/loginCheckCode","anon");
        filterMap.put("/user/indexByCode","anon");
        filterMap.put("/user/indexCheckCode","anon");
        filterMap.put("/user/indexBindEmail","anon");
        filterMap.put("/user/IndexEmailCode","anon");
        filterMap.put("/role/*","token");
        filterMap.put("/auth/*","token");
        filterMap.put("/user/*","token");
        filterMap.put("/org/*","token");
        shiroFilter.setFilterChainDefinitionMap(filterMap);

        return shiroFilter;
    }
    @Bean("lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator proxyCreator = new DefaultAdvisorAutoProxyCreator();
        proxyCreator.setProxyTargetClass(true);
        return proxyCreator;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }


}

