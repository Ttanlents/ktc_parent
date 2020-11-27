package com.ktc.friend.service;


import com.ktc.friend.client.UserClient;
import com.ktc.friend.dao.FriendDao;
import com.ktc.friend.pojo.Friend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import util.IdWorker;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author admin
 * @Description tb_friend 服务层
 * @date 2020-11-27 09:55:45
 */
@Service
public class FriendService {

    @Autowired
    private FriendDao friendDao;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private UserClient userClient;

    /**
     * 查询全部列表
     *
     * @return
     */
    public List<Friend> findAll() {
        return friendDao.findAll();
    }

    /**
     * 条件查询+分页
     *
     * @param whereMap
     * @param page
     * @param size
     * @return
     */
    public Page<Friend> findSearch(Map whereMap, int page, int size) {
        Specification<Friend> specification = createSpecification(whereMap);
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        return friendDao.findAll(specification, pageRequest);
    }

    /**
     * 条件查询
     *
     * @param whereMap
     * @return
     */
    public List<Friend> findSearch(Map whereMap) {
        Specification<Friend> specification = createSpecification(whereMap);
        return friendDao.findAll(specification);
    }

    /**
     * 根据ID查询实体
     *
     * @param id
     * @return
     */
    public Friend findById(String id) {
        return friendDao.findById(id).get();
    }

    /**
     * 增加
     *
     * @param friend
     */
    public void add(Friend friend) {
        friend.setUserid(idWorker.nextId() + "");
        friendDao.save(friend);
    }

    /**
     * 修改
     *
     * @param friend
     */
    public void update(Friend friend) {
        friendDao.save(friend);
    }

    /**
     * 删除
     *
     * @param id
     */
    public void deleteById(String id) {
        friendDao.deleteById(id);
    }

    /**
     * 动态条件构建
     *
     * @param searchMap
     * @return
     */
    private Specification<Friend> createSpecification(Map searchMap) {

        return new Specification<Friend>() {
            @Override
            public Predicate toPredicate(Root<Friend> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<Predicate>();
                return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));
            }
        };
    }

    @Transactional
    public Boolean like(String userId, String friendid) {
        Friend friend = friendDao.findByUseridAndFriendid(userId, friendid);
        if (friend != null) {
            //取消关注
            if (friend.getIslike().equals("1")) {
                //互粉的要改为0
                friendDao.update(friendid, userId, "0");
            }
            friendDao.delete(friend);
            userClient.updateFansCount(friendid,-1);
            userClient.updateFollowCount(userId,-1);
            return false;
        }

        //来加关注
        Friend oldFrend = friendDao.findByUseridAndFriendid(friendid, userId);
        if (oldFrend != null) {
            //关注过我
            Friend newFriend = new Friend(userId, friendid, "1");
            friendDao.save(newFriend);
            friendDao.update(friendid, userId, "1");
        } else {
            Friend newFriend = new Friend(userId, friendid, "0");
            friendDao.save(newFriend);
        }
        userClient.updateFollowCount(userId,1);
        userClient.updateFansCount(friendid,1);
        return true;

    }
}
