package com.ktc.friend.client;

import com.ktc.friend.client.impl.UserClientImpl;
import entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author 余俊锋
 * @date 2020/11/27 12:08
 * @Description
 */
@FeignClient(value = "ktc-user",fallback = UserClientImpl.class)  //熔断降级
public interface UserClient {
    @RequestMapping(value = "/user/updateFansCount/{userid}/{count}", method = RequestMethod.PUT)
     Result updateFansCount(@PathVariable("userid") String userid, @PathVariable("count") Integer count) ;

    /**
     * 更新关注数
     * @param userid 要更改的用户id
     * @param count 更改的数量
     * @return
     */
    @RequestMapping(value = "/user/updateFollowCount/{userid}/{count}", method = RequestMethod.PUT)
     Result updateFollowCount(@PathVariable("userid") String userid,@PathVariable("count") Integer count);
}
