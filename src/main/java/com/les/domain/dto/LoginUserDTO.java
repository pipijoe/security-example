package com.les.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Joetao
 * @desc 用户，一个用户可以有多个角色
 * @time 2020/3/19 3:18 PM
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginUserDTO {
    private String username;
    private String password;
}
