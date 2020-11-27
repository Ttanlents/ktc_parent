package com.ktc.qa.service;

import com.ktc.qa.dao.ProblemDao;
import com.ktc.qa.pojo.Problem;
import entity.*;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import util.IdWorker;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Description 问题 服务层
 * @author admin
 * @date 2020-11-23 18:13:04
*/
@Service
public class ProblemService {

	@Autowired
	private ProblemDao problemDao;

	@Autowired
	private IdWorker idWorker;
	@Autowired
	HttpServletRequest request;

	/**
	* 查询全部列表
	* @return
	*/
	public List<Problem> findAll() {
		return problemDao.findAll();
	}

	/**
	* 条件查询+分页
	* @param whereMap
	* @param page
	* @param size
	* @return
	*/
	public Page<Problem> findSearch(Map whereMap, int page, int size) {
		Specification<Problem> specification = createSpecification(whereMap);
		PageRequest pageRequest =  PageRequest.of(page-1, size);
		return problemDao.findAll(specification, pageRequest);
	}

	/**
 	* 条件查询
	* @param whereMap
	* @return
	*/
	public List<Problem> findSearch(Map whereMap) {
		Specification<Problem> specification = createSpecification(whereMap);
		return problemDao.findAll(specification);
	}

	/**
	* 根据ID查询实体
	* @param id
	* @return
	*/
	public Problem findById(String id) {
		return problemDao.findById(id).get();
	}

	/**
	* 增加
	* @param problem
	*/
	public Result add(Problem problem) {
		Claims claims_user = (Claims)request.getAttribute("claims_user");
		if (claims_user==null){
			return new Result(StatusCode.ACCESS_ERROR,false,"请先登录");
		}
		problem.setId( idWorker.nextId()+"" );
		problemDao.save(problem);
		return new Result(StatusCode.OK,true,"添加成功");
	}

	/**
	* 修改
	* @param problem
	*/
	public void update(Problem problem) {
		problemDao.save(problem);
	}

	/**
	* 删除
	* @param id
	*/
	public void deleteById(String id) {
		problemDao.deleteById(id);
	}

	/**
	* 动态条件构建
	* @param searchMap
	* @return
	*/
	private Specification<Problem> createSpecification(Map searchMap) {

		return new Specification<Problem>() {

			@Override
			public Predicate toPredicate(Root<Problem> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
				//标题
				if (searchMap.get("title")!=null && !"".equals(searchMap.get("title"))) {
					predicateList.add(cb.like(root.get("title").as(String.class), "%"+(String)searchMap.get("title")+"%"));
				}
				//内容
				if (searchMap.get("content")!=null && !"".equals(searchMap.get("content"))) {
					predicateList.add(cb.like(root.get("content").as(String.class), "%"+(String)searchMap.get("content")+"%"));
				}
				//创建日期
				if (searchMap.get("createtime")!=null && !"".equals(searchMap.get("createtime"))) {
					predicateList.add(cb.like(root.get("createtime").as(String.class), "%"+(String)searchMap.get("createtime")+"%"));
				}
				//修改日期
				if (searchMap.get("updatetime")!=null && !"".equals(searchMap.get("updatetime"))) {
					predicateList.add(cb.like(root.get("updatetime").as(String.class), "%"+(String)searchMap.get("updatetime")+"%"));
				}
				//用户ID
				if (searchMap.get("userid")!=null && !"".equals(searchMap.get("userid"))) {
					predicateList.add(cb.like(root.get("userid").as(String.class), "%"+(String)searchMap.get("userid")+"%"));
				}
				//昵称
				if (searchMap.get("nickname")!=null && !"".equals(searchMap.get("nickname"))) {
					predicateList.add(cb.like(root.get("nickname").as(String.class), "%"+(String)searchMap.get("nickname")+"%"));
				}
				//浏览量
				if (searchMap.get("visits")!=null && !"".equals(searchMap.get("visits"))) {
					predicateList.add(cb.like(root.get("visits").as(String.class), "%"+(String)searchMap.get("visits")+"%"));
				}
				//点赞数
				if (searchMap.get("thumbup")!=null && !"".equals(searchMap.get("thumbup"))) {
					predicateList.add(cb.like(root.get("thumbup").as(String.class), "%"+(String)searchMap.get("thumbup")+"%"));
				}
				//回复数
				if (searchMap.get("reply")!=null && !"".equals(searchMap.get("reply"))) {
					predicateList.add(cb.like(root.get("reply").as(String.class), "%"+(String)searchMap.get("reply")+"%"));
				}
				//是否解决
				if (searchMap.get("solve")!=null && !"".equals(searchMap.get("solve"))) {
					predicateList.add(cb.like(root.get("solve").as(String.class), "%"+(String)searchMap.get("solve")+"%"));
				}
				//回复人昵称
				if (searchMap.get("replyname")!=null && !"".equals(searchMap.get("replyname"))) {
					predicateList.add(cb.like(root.get("replyname").as(String.class), "%"+(String)searchMap.get("replyname")+"%"));
				}
				//回复日期
				if (searchMap.get("replytime")!=null && !"".equals(searchMap.get("replytime"))) {
					predicateList.add(cb.like(root.get("replytime").as(String.class), "%"+(String)searchMap.get("replytime")+"%"));
				}

				return cb.and( predicateList.toArray(new Predicate[predicateList.size()]));
			}
		};
	}

    public Page<Problem> newlist(String labelid, Integer page, Integer size) {
		return 	problemDao.findByLabelIdOrderByReplytime(labelid,PageRequest.of(page-1,size, Sort.by(Sort.Direction.DESC,"replytime")));

    }

	public Page<Problem> hotlist(String labelid, Integer page, Integer size) {
		return 	problemDao.findByLabelIdOrderByReply(labelid,PageRequest.of(page-1,size, Sort.by(Sort.Direction.DESC,"reply")));
	}

	public Page<Problem> waitlist(String labelid, Integer page, Integer size) {
		return problemDao.findByLabelId(labelid,PageRequest.of(page-1,size));
	}
}
