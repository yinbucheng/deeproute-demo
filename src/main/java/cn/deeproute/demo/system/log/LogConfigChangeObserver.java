package cn.deeproute.demo.system.log;

import cn.deeproute.demo.system.ConfigChangeObserver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
@Slf4j
public class LogConfigChangeObserver extends ConfigChangeObserver {

    @Override
    public void handle(String key, Object oldValue, Object newValue) {
        log.info("{} {}", oldValue, newValue);
    }

    @Override
    public Set<String> matchKey() {
        Set<String> hashSet = new HashSet<>();
        hashSet.add("deeproute.logging.maxLength");
        hashSet.add("deeproute.logging.lineNum");
        return hashSet;
    }
}
