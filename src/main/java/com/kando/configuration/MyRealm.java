package com.kando.configuration;
import com.kando.dao.UserDao;
import com.kando.entity.User;
import com.kando.service.impl.RoleServiceImpl;
import com.kando.util.MDCode;
import com.kando.util.UserNotExsistException;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
@Component
@Slf4j
public class MyRealm extends AuthorizingRealm {
	@Autowired
    private RoleServiceImpl authorityService;
	@Autowired
    UserDao userDao;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取登录名 -- 前端传过来的
        String phone = (String) principalCollection.getPrimaryPrincipal();
        //查询用户名称以及对应的角色 -- 从数据查


        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        Set<String> set = new HashSet<>();
//        set.add("youke:c");


        simpleAuthorizationInfo.setRoles(set);
        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //获取用户名，前端传的那个
        String phone = authenticationToken.getPrincipal().toString();
        //按照用户名从从数据库查询出密码
        User user = userDao.selectByphone(phone);
        String password="default";
        if(user!=null){
            password = user.getPassword();
        }
        //第二个参数为数据库查询出的密码
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(phone,password, getName());
        return simpleAuthenticationInfo;
    }
}

