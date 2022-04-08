package cn.deeproute.demo;

import cn.deeproute.demo.config.NacosConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableDiscoveryClient
@EnableConfigurationProperties({NacosConfig.class})
@EnableFeignClients(basePackages = "cn.deeproute.demo.feign" )
@MapperScan(basePackages = "cn.deeproute.demo.mapper")
@Slf4j
public class DeeprouteDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DeeprouteDemoApplication.class, args);
        log.info("Deeproute server start success");
    }
}
