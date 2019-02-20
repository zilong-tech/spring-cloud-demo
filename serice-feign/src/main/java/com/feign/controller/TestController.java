package com.feign.controller;

import com.feign.api.OpenService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Description:
 * <p>
 * Create on 2019/02/20
 *
 * @author zouyongsheng
 */
@RestController
public class TestController {

    @Resource
    private OpenService openService;

    @GetMapping(value = "/test/hi")
    public String hi(@RequestParam(value = "name")String name){
        return openService.test(name);

    }
}
