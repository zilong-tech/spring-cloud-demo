package com.feign.api;

import com.feign.hystric.OpenServiceHystric;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

    /**
     * 测试springcloud的超时机制
     * @param mills
     * @return
     */
    @RequestMapping(value = "/timeOut",method = RequestMethod.GET)
    String timeOut(@RequestParam(value = "mills") int mills);

}
