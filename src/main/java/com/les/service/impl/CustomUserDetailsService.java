package com.les.service.impl;

import com.les.domain.LoginUser;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;

/**
 * 登陆身份认证
 * @author: JoeTao
 * createAt: 2018/9/14
 */
@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //todo 根据用户名获取用户信息，一般是通过查询数据库用户表
         return LoginUser.builder()
                .id(1L)
                .username("admin")
                .name("管理员")
                .password("$2a$10$VcsS7kZU0MjxV3ls13q37Opmi.BXmJl4xloXG.g1biPE6G2COV8vq")
                .enabled(true)
                .authorities(Collections.singleton(new SimpleGrantedAuthority("ROLE_admin")))
                .build();
    }

    public static void main(String[] args) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        System.out.println(bCryptPasswordEncoder.encode("123456"));
    }
}
