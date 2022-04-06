package cn.deeproute.demo;

import cn.deeproute.demo.config.NacosConfig;
import com.purgeteam.dynamic.config.starter.annotation.EnableDynamicConfigEvent;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.config.Configurator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableDiscoveryClient
@EnableConfigurationProperties({NacosConfig.class})
@EnableFeignClients(basePackages = "cn.deeproute.demo.feign")
@MapperScan(basePackages = "cn.deeproute.demo.mapper")
@Slf4j
@EnableDynamicConfigEvent
public class DeeprouteDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DeeprouteDemoApplication.class, args);
//        try {
//            Thread.sleep(1000*30);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        Configurator.setAllLevels(LogManager.getRootLogger().getName(), Level.DEBUG);
        log.info("Deeproute server start success");
    }
}
