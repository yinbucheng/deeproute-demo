package cn.deeproute.demo.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "dataocean-file-management-service", path = "/file",contextId = "test")
public interface RemoteClient {
    @PostMapping(value = "test")
    String test();
}
