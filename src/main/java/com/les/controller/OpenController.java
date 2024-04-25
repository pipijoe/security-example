package com.les.controller;

import com.les.common.utils.RsaUtil;
import com.les.domain.ResultJson;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: joetao
 * @Date: 2024/4/18 9:21
 */
@RequestMapping("/api/v1")
@RestController
public class OpenController {

    @GetMapping("/getPublicKey")
    public ResultJson<String> getPublicKey() {
        return ResultJson.ok(RsaUtil.getPublicKey());
    }
}
