package com.ktc.friend.client.impl;

import com.ktc.friend.client.UserClient;
import entity.Result;
import entity.StatusCode;
import org.springframework.stereotype.Component;

/**
 * @author 余俊锋
 * @date 2020/11/27 16:46
 * @Description
 */
@Component
public class UserClientImpl implements UserClient {
    @Override
    public Result updateFansCount(String userid, Integer count) {
        return new Result(StatusCode.ERROR,false,"服务不可用，请重试！");
    }

    @Override
    public Result updateFollowCount(String userid, Integer count) {
        return new Result(StatusCode.ERROR,false,"服务不可用，请重试！");
    }
}
