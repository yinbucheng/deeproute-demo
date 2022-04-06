package cn.deeproute.demo.system.sql;

import cn.deeproute.demo.system.AbstractProfileEnvironmentPostProcessor;
import cn.deeproute.demo.system.PropertySourceUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.core.env.ConfigurableEnvironment;

public class MySQLEnvInjector extends AbstractProfileEnvironmentPostProcessor {
    @Override
    public void onDev(ConfigurableEnvironment env, SpringApplication application) {

    }

    @Override
    public void onStg(ConfigurableEnvironment env, SpringApplication application) {

    }

    @Override
    public void onPrd(ConfigurableEnvironment env, SpringApplication application) {

    }

    @Override
    public void onLocal(ConfigurableEnvironment env, SpringApplication application) {
        //注入数据库配置
        PropertySourceUtils.put(env, "spring.datasource.url", "jdbc:mysql://10.3.0.1:3306/data_ocean_dev?createDatabaseIfNotExist=false&useUnicode=yes&characterEncoding=UTF-8&useLegacyDatetimeCode=false&serverTimezone=Asia/Shanghai");
        PropertySourceUtils.put(env, "spring.datasource.username", "root");
        PropertySourceUtils.put(env,"spring.datasource.password","test123!");
        PropertySourceUtils.put(env,"spring.datasource.driver-class-name","com.mysql.cj.jdbc.Driver");
        PropertySourceUtils.put(env,"mybatis.configuration.map-underscore-to-camel-case","true");
        PropertySourceUtils.put(env,"mybatis.type-aliases-package","cn.deeproute.demo.model.po");
        PropertySourceUtils.put(env,"mybatis.mapper-locations","classpath*:mapper/*.xml");
    }

    @Override
    public void onAllProfiles(ConfigurableEnvironment env, SpringApplication application) {

    }
}
