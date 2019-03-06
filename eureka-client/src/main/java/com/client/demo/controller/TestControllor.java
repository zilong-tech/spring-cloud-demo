package com.client.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description:
 * <p>
 * Create on 2019/03/05
 *
 * @author zouyongsheng
 */
@RestController
@Slf4j
public class TestControllor {

    /**
     * 测试重试时间
     *
     * @return
     */
    @RequestMapping("/timeOut")
    public String timeOut(@RequestParam int mills) {

        log.info("[client服务-] [timeOut方法]收到请求,阻塞{}ms", mills);
        try {
            Thread.sleep(mills);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("[client服务-{}] [timeOut]返回请求");
        return String.format("client服务请求ok!!!");
    }

}
