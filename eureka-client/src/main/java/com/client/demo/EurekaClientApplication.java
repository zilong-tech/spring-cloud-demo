package com.client.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description:
 * <p>
 * Create on 2019/01/30
 *
 * @author zouyongsheng
 */

/**
 * @EnableEurekaClient 表明是一个eurekaclient
 */
@SpringBootApplication
@EnableEurekaClient
@RestController
public class EurekaClientApplication {

    public static void main(String[] args) {

        SpringApplication.run(EurekaClientApplication.class);

    }

    @RequestMapping("/hi")
    public String home(@RequestParam String name) {
        return "hi "+name;
    }
}
