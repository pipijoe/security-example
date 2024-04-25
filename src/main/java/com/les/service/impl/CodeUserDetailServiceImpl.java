package com.les.service.impl;

import com.les.domain.LoginUser;
import com.les.service.interfaces.CodeUserDetailService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Collections;

/**
 * @Author: joetao
 * @Date: 2024/4/18 15:58
 */
@Service
public class CodeUserDetailServiceImpl implements CodeUserDetailService {
    @Override
    public LoginUser queryRemoteUserByCode(String code) {
        //todo 根据授权码获取来自认证中心的用户信息
        return LoginUser.builder()
                .id(1L)
                .username("admin")
                .name("管理员")
                .password("")
                .enabled(true)
                .authorities(Collections.singleton(new SimpleGrantedAuthority("ROLE_admin")))
                .build();
    }
}
