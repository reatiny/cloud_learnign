package cn.itcast.user.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 配置热更新的方式二，更为推荐
 */
@Data
@Component
@ConfigurationProperties(prefix = "pattern")
public class PatternProperties {
    private String dateformat;
    private String envShareValue;
    // nacos、服务本地同时出现相同属性时，优先级有高低之分
    // service-dev.yaml > service.yaml > 本地配置
    private String name;
}
