package com.ktc.friend.service;

import com.ktc.friend.dao.NoFriendDao;
import com.ktc.friend.pojo.NoFriend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import util.IdWorker;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Description tb_nofriend 服务层
 * @author admin
 * @date 2020-11-27 11:11:17
*/
@Service
public class NoFriendService {

	@Autowired
	private NoFriendDao noFriendDao;

	@Autowired
	private IdWorker idWorker;

	/**
	* 查询全部列表
	* @return
	*/
	public List<NoFriend> findAll() {
		return noFriendDao.findAll();
	}

	/**
	* 条件查询+分页
	* @param whereMap
	* @param page
	* @param size
	* @return
	*/
	public Page<NoFriend> findSearch(Map whereMap, int page, int size) {
		Specification<NoFriend> specification = createSpecification(whereMap);
		PageRequest pageRequest =  PageRequest.of(page-1, size);
		return noFriendDao.findAll(specification, pageRequest);
	}

	/**
 	* 条件查询
	* @param whereMap
	* @return
	*/
	public List<NoFriend> findSearch(Map whereMap) {
		Specification<NoFriend> specification = createSpecification(whereMap);
		return noFriendDao.findAll(specification);
	}

	/**
	* 根据ID查询实体
	* @param id
	* @return
	*/
	public NoFriend findById(String id) {
		return noFriendDao.findById(id).get();
	}

	/**
	* 增加
	* @param noFriend
	*/
	public void add(NoFriend noFriend) {
		noFriend.setFriendid(idWorker.nextId()+"");
		noFriendDao.save(noFriend);
	}

	/**
	* 修改
	* @param noFriend
	*/
	public void update(NoFriend noFriend) {
		noFriendDao.save(noFriend);
	}

	/**
	* 删除
	* @param id
	*/
	public void deleteById(String id) {
		noFriendDao.deleteById(id);
	}

	/**
	* 动态条件构建
	* @param searchMap
	* @return
	*/
	private Specification<NoFriend> createSpecification(Map searchMap) {

		return new Specification<NoFriend>() {
			@Override
			public Predicate toPredicate(Root<NoFriend> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
				return cb.and( predicateList.toArray(new Predicate[predicateList.size()]));
			}
		};
	}

	public Boolean addNoFriend(String userid, String friendid) {
		int count= noFriendDao.countByUseridAndFriendid(userid, friendid);

		if (count>0){
			//拉黑过了
			return false;
		}

		noFriendDao.save(new NoFriend(userid,friendid));
		return true;
	}
}
