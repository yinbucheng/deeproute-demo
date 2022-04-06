package cn.deeproute.demo.system;

import com.purgeteam.dynamic.config.starter.event.ActionConfigEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author chongyin
 */
@Slf4j
public class ConfigChangeListener implements ApplicationListener<ActionConfigEvent>, ApplicationContextAware {

    public static final String BEFORE = "before";
    public static final String AFTER = "after";
    List<ConfigChangeObserver> observers = new LinkedList<>();

    @Override
    public void onApplicationEvent(ActionConfigEvent actionConfigEvent) {
        log.info("config happen change {} {}", actionConfigEvent.getEventDesc(), actionConfigEvent.getPropertyMap());
        Map<String, HashMap> propertyMap = actionConfigEvent.getPropertyMap();
        for(ConfigChangeObserver observer:observers){
            for(Map.Entry<String,HashMap> entry:propertyMap.entrySet()) {
                String key = entry.getKey();
                HashMap map = entry.getValue();
                observer.configChange(key, map.get(BEFORE), map.get(AFTER));
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
