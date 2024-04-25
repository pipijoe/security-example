package com.les.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * @author Joetao
 * @time 2020/3/20 3:52 PM
 */
@Data
@Builder
public class UserVO {
    private Long id;
    private String username;
    private String name;
    private List<String> roles;
    private String token;
    private String refreshToken;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime expiredAt;
}
