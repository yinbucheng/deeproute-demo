package cn.deeproute.demo.system;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;

import java.util.Arrays;
import java.util.List;

/**
 * @author test
 * @create 2019/11/11 21:02
 * @description
 */
@Slf4j
public abstract class AbstractProfileEnvironmentPostProcessor implements EnvironmentPostProcessor {
    public static final String LOCAL ="local";
    public static final String STG = "stg";
    public static final String DEV = "dev";
    public static final String PRD = "prd";
    public static final String IT = "it";


    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        //如果boot未准备好直接放行
        if (!SpringBootReady.isReady()) {
            return;
        }
        String[] actives = environment.getActiveProfiles();
        List<String> profileList = Arrays.asList(actives);
        if(profileList.contains(PRD)){
            onPrd(environment,application);
        }else if (profileList.contains(STG)){
            onStg(environment,application);
        }else if(profileList.contains(DEV)){
            onDev(environment,application);
        }else if (profileList.contains(LOCAL)){
            onLocal(environment,application);
        }
        onAllProfiles(environment,application);
    }


    public abstract void onDev(ConfigurableEnvironment env,SpringApplication application);

    public abstract void onStg(ConfigurableEnvironment env,SpringApplication application);

    public abstract void onPrd(ConfigurableEnvironment env,SpringApplication application);

    public abstract void onLocal(ConfigurableEnvironment env,SpringApplication application) ;

    public abstract void onAllProfiles(ConfigurableEnvironment env,SpringApplication application);

}