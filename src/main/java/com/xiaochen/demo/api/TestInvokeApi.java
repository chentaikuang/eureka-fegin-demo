package com.xiaochen.demo.api;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "MS-PROVIDER-1")
public interface TestInvokeApi {

    @RequestMapping(value = "/test/tips")
    public String tips(@RequestParam(value = "name") String name);

    @RequestMapping(value = "/test/invokeGet")
    public String invokeGet(@RequestParam(value = "tips") String tips);

    @PostMapping(value = "/test/invokePost")
    public String invokePost(@RequestParam(value = "tips") String tips);
}
