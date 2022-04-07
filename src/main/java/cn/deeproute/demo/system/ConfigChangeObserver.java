package cn.deeproute.demo.system;

import java.util.Set;

/**
 * @author chongyin
 */
public abstract class ConfigChangeObserver {
    abstract public void handle(String key,Object val);

    abstract public Set<String> matchKey();

    protected void configChange(String key,Object val) {
        if (!matchKey().contains(key)) {
            return;
        }
        handle(key,val);
    }
}
