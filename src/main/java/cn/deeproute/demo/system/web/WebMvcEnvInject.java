package cn.deeproute.demo.system.web;

import cn.deeproute.demo.system.AbstractProfileEnvironmentPostProcessor;
import cn.deeproute.demo.system.PropertySourceUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * @author test
 * @create 2019/11/11 21:00
 * @description
 */
public class WebMvcEnvInject extends AbstractProfileEnvironmentPostProcessor {

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

    }

    public void onAllProfiles(ConfigurableEnvironment env, SpringApplication application) {
        PropertySourceUtils.put(env, "spring.mvc.throw-exception-if-no-handler-found", true);
        PropertySourceUtils.put(env, "spring.resources.add-mappings", false);
        PropertySourceUtils.put(env, "spring.http.encoding.charset", "utf-8");
        PropertySourceUtils.put(env, "spring.http.encoding.force", true);
        PropertySourceUtils.put(env, "spring.http.encoding.enabled", true);
    }
}