package cn.deeproute.demo.system.log;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author test
 * @create 2022/2/27 22:10
 * @description
 */
@Configuration
@EnableConfigurationProperties(DeeprouteLogProperties.class)
public class DeeprouteAutoConfiguration {
}
