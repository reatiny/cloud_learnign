package cn.itcast.feign.config;


import feign.Logger;
import org.springframework.context.annotation.Bean;

/**
 * 配置文件方式来实现feign修改日志级别
 * 如果要全局生效，将其放到启动类的@EnableFeignClients这个注解中：
 *      @EnableFeignClients(defaultConfiguration = DefaultFeignConfiguration.class)
 *
 * 如果是局部生效，则把它放到对应的@FeignClient这个注解中：
 *      @FeignClient(value = "userservice", configuration = DefaultFeignConfiguration.class)
 */
public class DefaultFeignConfiguration {
    @Bean
    public Logger.Level feignLogLever() {
        // 日志级别为BASIC
        return Logger.Level.BASIC;
    }
}
