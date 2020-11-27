package com.ktc.user.service;

import com.ktc.user.dao.UserDao;
import com.ktc.user.pojo.User;
import entity.*;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import util.IdWorker;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @Description 用户 服务层
 * @author admin
 * @date 2020-11-25 17:56:10
*/
@Service
public class UserService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private IdWorker idWorker;
	@Autowired
	RedisTemplate redisTemplate;
	@Autowired
	RabbitTemplate rabbitTemplate;
	@Autowired
	BCryptPasswordEncoder encoder;

	/**
	* 查询全部列表
	* @return
	*/
	public List<User> findAll() {
		return userDao.findAll();
	}

	/**
	* 条件查询+分页
	* @param whereMap
	* @param page
	* @param size
	* @return
	*/
	public Page<User> findSearch(Map whereMap, int page, int size) {
		Specification<User> specification = createSpecification(whereMap);
		PageRequest pageRequest =  PageRequest.of(page-1, size);
		return userDao.findAll(specification, pageRequest);
	}

	/**
 	* 条件查询
	* @param whereMap
	* @return
	*/
	public List<User> findSearch(Map whereMap) {
		Specification<User> specification = createSpecification(whereMap);
		return userDao.findAll(specification);
	}

	/**
	* 根据ID查询实体
	* @param id
	* @return
	*/
	public User findById(String id) {
		return userDao.findById(id).get();
	}

	/**
	* 增加
	* @param user
	*/
	public void add(User user) {
		user.setId( idWorker.nextId()+"" );
		userDao.save(user);
	}

	/**
	* 修改
	* @param user
	*/
	public void update(User user) {
		userDao.save(user);
	}

	/**
	* 删除
	* @param id
	*/
	public void deleteById(String id) {
		userDao.deleteById(id);
	}

	/**
	* 动态条件构建
	* @param searchMap
	* @return
	*/
	private Specification<User> createSpecification(Map searchMap) {

		return new Specification<User>() {

			@Override
			public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
				//手机号码
				if (searchMap.get("mobile")!=null && !"".equals(searchMap.get("mobile"))) {
					predicateList.add(cb.like(root.get("mobile").as(String.class), "%"+(String)searchMap.get("mobile")+"%"));
				}
				//密码
				if (searchMap.get("password")!=null && !"".equals(searchMap.get("password"))) {
					predicateList.add(cb.like(root.get("password").as(String.class), "%"+(String)searchMap.get("password")+"%"));
				}
				//昵称
				if (searchMap.get("nickname")!=null && !"".equals(searchMap.get("nickname"))) {
					predicateList.add(cb.like(root.get("nickname").as(String.class), "%"+(String)searchMap.get("nickname")+"%"));
				}
				//性别
				if (searchMap.get("sex")!=null && !"".equals(searchMap.get("sex"))) {
					predicateList.add(cb.like(root.get("sex").as(String.class), "%"+(String)searchMap.get("sex")+"%"));
				}
				//出生年月日
				if (searchMap.get("birthday")!=null && !"".equals(searchMap.get("birthday"))) {
					predicateList.add(cb.like(root.get("birthday").as(String.class), "%"+(String)searchMap.get("birthday")+"%"));
				}
				//头像
				if (searchMap.get("avatar")!=null && !"".equals(searchMap.get("avatar"))) {
					predicateList.add(cb.like(root.get("avatar").as(String.class), "%"+(String)searchMap.get("avatar")+"%"));
				}
				//E-Mail
				if (searchMap.get("email")!=null && !"".equals(searchMap.get("email"))) {
					predicateList.add(cb.like(root.get("email").as(String.class), "%"+(String)searchMap.get("email")+"%"));
				}
				//注册日期
				if (searchMap.get("regdate")!=null && !"".equals(searchMap.get("regdate"))) {
					predicateList.add(cb.like(root.get("regdate").as(String.class), "%"+(String)searchMap.get("regdate")+"%"));
				}
				//修改日期
				if (searchMap.get("updatedate")!=null && !"".equals(searchMap.get("updatedate"))) {
					predicateList.add(cb.like(root.get("updatedate").as(String.class), "%"+(String)searchMap.get("updatedate")+"%"));
				}
				//最后登陆日期
				if (searchMap.get("lastdate")!=null && !"".equals(searchMap.get("lastdate"))) {
					predicateList.add(cb.like(root.get("lastdate").as(String.class), "%"+(String)searchMap.get("lastdate")+"%"));
				}
				//在线时长（分钟）
				if (searchMap.get("online")!=null && !"".equals(searchMap.get("online"))) {
					predicateList.add(cb.like(root.get("online").as(String.class), "%"+(String)searchMap.get("online")+"%"));
				}
				//兴趣
				if (searchMap.get("interest")!=null && !"".equals(searchMap.get("interest"))) {
					predicateList.add(cb.like(root.get("interest").as(String.class), "%"+(String)searchMap.get("interest")+"%"));
				}
				//个性
				if (searchMap.get("personality")!=null && !"".equals(searchMap.get("personality"))) {
					predicateList.add(cb.like(root.get("personality").as(String.class), "%"+(String)searchMap.get("personality")+"%"));
				}
				//粉丝数
				if (searchMap.get("fanscount")!=null && !"".equals(searchMap.get("fanscount"))) {
					predicateList.add(cb.like(root.get("fanscount").as(String.class), "%"+(String)searchMap.get("fanscount")+"%"));
				}
				//关注数
				if (searchMap.get("followcount")!=null && !"".equals(searchMap.get("followcount"))) {
					predicateList.add(cb.like(root.get("followcount").as(String.class), "%"+(String)searchMap.get("followcount")+"%"));
				}

				return cb.and( predicateList.toArray(new Predicate[predicateList.size()]));
			}
		};
	}

    public User login(String nickname) {
		return  userDao.findBymobile(nickname);
    }

	public void sendsms(String mobile) {
		String code = RandomStringUtils.randomNumeric(6);
		//存入redis  以及  rabbitMQ
		redisTemplate.opsForValue().set("sms:"+mobile,code,60, TimeUnit.SECONDS);

		Map<Object, Object> map = new HashMap<>();
		map.put("mobile",mobile);
		map.put("code",code);
		rabbitTemplate.convertAndSend("sms",map);

	}

	public Result register(User user, String code) {
		User existUser = userDao.findBymobile(user.getMobile());
		if (existUser!=null){
			return new Result(StatusCode.ERROR, false, "该手机号已近被注册");
		}
		ValueOperations ops = redisTemplate.opsForValue();
		String redisCode =(String) ops.get("sms:" + user.getMobile());
		if (redisCode==null||"".equals(redisCode)||!redisCode.equals(code)){
			return new Result(StatusCode.ERROR, false, "验证码错误或已过期");
		}
		user.setPassword(encoder.encode(user.getPassword()));
		user.setId(idWorker.nextId()+"");
		user.setFollowcount(0);
		user.setFanscount(0);
		user.setOnline(0L);
		user.setUpdatedate(new Date());
		user.setRegdate(new Date());
		user.setLastdate(new Date());
		userDao.save(user);
		return new Result(StatusCode.OK, true, "注册成功");
	}

	@Transactional
	public void updateFansCount(String userid, Integer count) {
		userDao.updateFansCount(userid,count);

	}
	@Transactional
	public void updateFollowCount(String userid, Integer count) {
		userDao.updateFollowCount(userid,count);
	}
}
