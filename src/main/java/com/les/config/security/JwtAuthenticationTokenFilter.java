package com.les.config.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * token校验过滤器
 * 当登录成功后，每次请求携带token进行访问，该过滤器会获取token，并从token解析出用户信息
 * 成功解析出用户信息后设置SecurityContextHolder为登录状态
 * @author: JoeTao
 * createAt: 2018/9/14
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        //todo 校验token， 并将token解析出的用户信息保存到SecurityContextHolder中
        chain.doFilter(request, response);
    }
}
