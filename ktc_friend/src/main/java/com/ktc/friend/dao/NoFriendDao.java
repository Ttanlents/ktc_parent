package com.ktc.friend.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.ktc.friend.pojo.NoFriend;

/**
 * @Description tb_nofriend 数据访问接口
 * @date 2020-11-27 11:11:15
 */
public interface NoFriendDao extends JpaRepository<NoFriend, String>, JpaSpecificationExecutor<NoFriend> {
    NoFriend findByUseridAndFriendid(String userid, String friendid);

    int countByUseridAndFriendid(String userid, String friendid);
}

