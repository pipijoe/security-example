package com.les.service.interfaces;

import com.les.domain.LoginUser;

/**
 * @Author: joetao
 * @Date: 2024/4/18 15:57
 */
public interface CodeUserDetailService {
    LoginUser queryRemoteUserByCode(String code);
}
