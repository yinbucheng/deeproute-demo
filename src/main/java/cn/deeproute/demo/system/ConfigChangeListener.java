package cn.deeproute.demo.system;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.cloud.context.environment.EnvironmentChangeEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * @author chongyin
 */
@Slf4j
public class ConfigChangeListener implements ApplicationListener<EnvironmentChangeEvent>, ApplicationContextAware {

    List<ConfigChangeObserver> observers = new LinkedList<>();

    @Override
    public void onApplicationEvent(EnvironmentChangeEvent event) {
        ConfigurableApplicationContext context = (ConfigurableApplicationContext) event.getSource();
        Set<String> keys = event.getKeys();
        for(ConfigChangeObserver observer:observers){
            for(String key:keys){
                observer.configChange(key,context.getEnvironment().getProperty(key));
            }
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        String[] beanNamesForType = applicationContext.getBeanNamesForType(ConfigChangeObserver.class);
        for (String beanName : beanNamesForType) {
            observers.add(applicationContext.getBean(beanName, ConfigChangeObserver.class));
        }
    }
}
