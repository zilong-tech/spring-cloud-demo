package com.feign.controller;

import com.feign.api.OpenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
@Slf4j
public class TestController {

    @Resource
    private OpenService openService;

    @GetMapping(value = "/test/hi")
    public String hi(@RequestParam(value = "name")String name){
        return openService.test(name);

    }


    /**
     * 测试重试时间
     * @return
     */
    @RequestMapping("/timeOut")
    public String timeOut(@RequestParam int mills){
        log.info("开始调用");
        return openService.timeOut( mills );
    }

}
