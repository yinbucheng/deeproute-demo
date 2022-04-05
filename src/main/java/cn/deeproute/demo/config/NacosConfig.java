package cn.deeproute.demo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "nacos.test")
public class NacosConfig {
    private String name;
    private Integer age;
    private String remark;
}
