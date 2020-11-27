package com.ktc.user.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.ktc.user.pojo.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * @Description 用户 数据访问接口
 * @date 2020-11-25 17:56:08
*/
public interface UserDao extends JpaRepository<User,String>,JpaSpecificationExecutor<User>{


    User findBymobile(String mobile);

    @Query("update User user set user.fanscount=user.fanscount+?2 where user.id=?1")
    @Modifying
    void updateFansCount(String userid, Integer count);


    @Query("update User user set user.followcount=user.followcount+?2 where user.id=?1")
    @Modifying
    void updateFollowCount(String userid, Integer count);
}

