package com.xiaochen.demo.controller;

import com.xiaochen.demo.api.TestInvokeApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/test")
public class TestController {

    @Autowired
    TestInvokeApi testApi;

    /**
     * visit url:http://localhost:8888/test/tips
     * @return
     */
    @GetMapping("/tips")
    public Map tips(){
        Map map = new HashMap();
        map.put("tips","caller");
        map.put("curTime",new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
        return map;
    }

    /**
     * http://localhost:8883/test/api/xiaochen
     * @param name
     * @return
     */
    @RequestMapping("/api/{name}")
    public String api(@PathVariable("name") String name) {
        return testApi.tips(name);
    }

    /**
     * http://localhost:8883/test/invoke/xiaochen
     * @param name
     * @return
     */
    @RequestMapping("/invoke/{name}")
    public String invoke(@PathVariable("name") String name) {
        if ("POST".equalsIgnoreCase(name)){
            return testApi.invokePost(name);
        }
        return testApi.invokeGet(name);
    }
}
