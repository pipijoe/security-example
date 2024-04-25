package com.les.config.security.codeauth;

import org.springframework.security.authentication.AbstractAuthenticationToken;

/**
 * 授权码认证对象
 * 注：这个token没有设置角色信息，根据实际情况进行添加
 * @Author: joetao
 * @Date: 2024/4/18 15:54
 */
public class CodeAuthenticationToken extends AbstractAuthenticationToken {
    private Object principal;
    public CodeAuthenticationToken(Object principal) {
        super(null);
        this.principal = principal;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return this.principal;
    }

    public void setPrincipal(Object principal) {
        this.principal = principal;
    }
}
