package cn.deeproute.demo.runner;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class TestRunner implements ApplicationRunner, EnvironmentAware {
    private ConfigurableEnvironment environment;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        String property = environment.getProperty("deeproute.logging.showSql");
        System.out.println(property);
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = (ConfigurableEnvironment) environment;
    }
}
