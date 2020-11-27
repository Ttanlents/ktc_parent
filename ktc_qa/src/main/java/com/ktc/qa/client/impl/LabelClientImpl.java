package com.ktc.qa.client.impl;

import com.ktc.qa.client.LabelClient;
import entity.Result;
import entity.StatusCode;
import org.springframework.stereotype.Component;

/**
 * @author 余俊锋
 * @date 2020/11/27 17:43
 * @Description
 */
@Component
public class LabelClientImpl implements LabelClient {
    @Override
    public Result findById(String id) {
        return new Result(StatusCode.ERROR,false,"服务开小差了，请重试！");
    }
}
