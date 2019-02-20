package com.feign.hystric;

import com.feign.api.OpenService;
import org.springframework.stereotype.Component;

/**
 * Description:
 * <p>
 * Create on 2019/02/20
 *
 * @author zouyongsheng
 */
@Component
public class OpenServiceHystric implements OpenService {
    @Override
    public String test(String name) {
        return "微服务故障";
    }
}
