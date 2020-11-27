package com.ktc.qa.client;


import com.ktc.qa.client.impl.LabelClientImpl;
import entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author 余俊锋
 * @date 2020/11/26 20:11
 * @Description
 */
@FeignClient(value = "ktc-base",fallback = LabelClientImpl.class)
public interface LabelClient {
    @RequestMapping(value = "/label/{id}", method = RequestMethod.GET)
    Result findById(@PathVariable("id") String id);
}
