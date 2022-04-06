package cn.deeproute.demo.controller;

import cn.deeproute.demo.config.NacosConfig;
import cn.deeproute.demo.model.vo.PageVO;
import cn.deeproute.demo.model.vo.TestVO;
import cn.deeproute.demo.service.ITestService;
import cn.deeproute.demo.system.time.PrintArgs;
import cn.deeproute.demo.system.time.PrintRT;
import cn.deeproute.demo.system.time.PrintTime;
import cn.deeproute.demo.system.web.NotAutoWrap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yinchong
 * @create 2022/4/5 13:59
 * @description
 */
@RestController
@RequestMapping("test")
public class TestController {
    @Autowired
    private ITestService testService;
    @Autowired
    private NacosConfig config;

    @RequestMapping(value = "helloWord", produces = MediaType.APPLICATION_JSON_VALUE)
    @PrintTime
    public Object helloWord() {
        return "hello word";
    }


    @RequestMapping(value = "save", produces = MediaType.APPLICATION_JSON_VALUE)
    @PrintArgs
    public Object save(@RequestBody @Validated TestVO vo) {
        testService.save(vo);
        return "save success";
    }

    @RequestMapping(value = "update", produces = MediaType.APPLICATION_JSON_VALUE)
    @PrintTime
    public Object update(@RequestBody @Validated TestVO vo) {
        testService.updateById(vo);
        return "success";
    }

    @RequestMapping(value = "config", produces = MediaType.APPLICATION_JSON_VALUE)
    @PrintRT
    public Object config() {
        return config;
    }

    @RequestMapping(value = "list", produces = MediaType.APPLICATION_JSON_VALUE)
    @PrintArgs
    public Object list(@RequestBody PageVO vo) {
        return testService.list(vo);
    }
}
