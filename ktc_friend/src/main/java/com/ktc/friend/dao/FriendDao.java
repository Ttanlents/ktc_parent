package com.ktc.friend.dao;

import com.ktc.friend.pojo.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * @Description tb_friend 数据访问接口
 * @date 2020-11-27 09:55:44
 */
public interface FriendDao extends JpaRepository<Friend, String>, JpaSpecificationExecutor<Friend> {

    Friend findByUseridAndFriendid(String userId, String friendid);

    @Query("update Friend f set f.islike=?3 where f.userid=?1 and f.friendid=?2")
    @Modifying
    void update(String userId, String friendId, String islike);


}

