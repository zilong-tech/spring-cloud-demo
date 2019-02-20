package com.feign.api;

import com.feign.hystric.OpenServiceHystric;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Description:
 * <p>
 * Create on 2019/02/20
 *
 * @author zouyongsheng
 */
@FeignClient(value = "EUREKA-CLIENT",fallback = OpenServiceHystric.class)
public interface OpenService {

    @GetMapping(value = "/hi")
    public String test(@RequestParam(value = "name") String name);
}
