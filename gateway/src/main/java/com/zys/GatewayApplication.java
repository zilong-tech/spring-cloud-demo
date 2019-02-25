package com.zys;

import com.zys.filter.RequestTimeFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * Description:
 * <p>
 * Create on 2019/02/25
 *
 * @author zouyongsheng
 */
@SpringBootApplication
@RestController
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class,args);
    }

    @Bean
    public RouteLocator myRoute(RouteLocatorBuilder builder){

        return builder.routes()
                .route(predicates -> predicates
                                     .path("/get")
                                     .filters(f -> f.addRequestHeader("hello","world"))
                                     .uri("http://httpbin.org:80")
                      )

                .route( p -> p
                             .host("*.hystrix.com")
                             .filters(f -> f
                                        .hystrix(config -> config
                                        .setName("mycmd")
                                        .setFallbackUri("forward:/fallback")))
                             .uri("http://httpbin.org:80")
                        )

                .route(a -> a.path("/custom/**")
                        .filters(f -> f.filter(new RequestTimeFilter())
                        .addResponseHeader("X-Response-Default-Foo", "Default-Bar"))
                        .uri("http://httpbin.org:80/get")
                        .order(0)
                        .id("customer_filter_router")
                        )
                .build();
    }

    @RequestMapping("/fallback")
    public Mono<String> fallback() {
        return Mono.just("fallback");
    }
}
