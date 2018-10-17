package com.xiaochen.demo.controller;

import com.google.gson.Gson;
import com.xiaochen.demo.resp.RestTemplateRespData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/rest")
public class RestTemplateController {

    private static final String INVOKEN_GET_URL = "http://localhost:8882/test/invokeGet?";
    private static final String INVOKEN_POST_URL = "http://localhost:8882/test/invokePost";

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    RestTemplate restTemplate;

    /**
     * visit url:http://localhost:8883/test/tips
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
     * http://localhost:8883/rest/invoke/get
     * http://localhost:8883/rest/invoke/post
     * @param name
     * @return
     */
    @RequestMapping("/invoke/{name}")
    public String invoke(@PathVariable("name") String name) {

        logger.info("===>>getParam :" + name);
        String resultStr = null;
        RestTemplateRespData respData = null;
        if ("POST".equalsIgnoreCase(name)){
            MultiValueMap<String, String> requestEntity = new LinkedMultiValueMap<>();
            requestEntity.add("tips",name);
            respData = restTemplate.postForObject(INVOKEN_POST_URL, requestEntity, RestTemplateRespData.class);
        }else {
            Map map = new HashMap();
            map.put("tips",name);
            respData  = restTemplate.getForObject(getGetUrl(map), RestTemplateRespData.class);
        }
        if (respData != null){
            resultStr = new Gson().toJson(respData);
        }
        logger.info("===>>get resultStr :" + resultStr);
        return resultStr;
    }

    private String getGetUrl(Map<String,String> map) {
        StringBuffer stringBuffer = new StringBuffer(INVOKEN_GET_URL);
        for(Map.Entry entry:map.entrySet()){
            stringBuffer.append(entry.getKey()).append("=").append(entry.getValue());
        }
        return stringBuffer.toString();
    }
}
