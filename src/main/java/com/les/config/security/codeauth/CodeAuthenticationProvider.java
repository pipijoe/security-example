package com.les.config.security.codeauth;

import com.les.domain.LoginUser;
import com.les.exception.LoginFailedException;
import com.les.service.interfaces.CodeUserDetailService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

/**
 * 授权码认证类
 * 认证成功逻辑：通过授权码能从认证中心获取用户信息就算认证成功
 * @Author: joetao
 * @Date: 2024/4/18 16:00
 */
public class CodeAuthenticationProvider implements AuthenticationProvider {
    private final CodeUserDetailService codeUserDetailService;

    public CodeAuthenticationProvider(CodeUserDetailService codeUserDetailService) {
        this.codeUserDetailService = codeUserDetailService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        CodeAuthenticationToken codeAuthenticationToken = (CodeAuthenticationToken) authentication;
        String code = codeAuthenticationToken.getPrincipal().toString();
        LoginUser loginUser = codeUserDetailService.queryRemoteUserByCode(code);
        if (loginUser == null) {
            throw new LoginFailedException("无法获取认证中心用户信息", code);
        }
        codeAuthenticationToken.setPrincipal(loginUser);
        codeAuthenticationToken.setAuthenticated(true);
        return codeAuthenticationToken;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return CodeAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
