package com.ktc.qa.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.ktc.qa.pojo.Reply;

/**
 * @Description 回答 数据访问接口
 * @date 2020-11-23 18:14:27
*/
public interface ReplyDao extends JpaRepository<Reply,String>,JpaSpecificationExecutor<Reply>{

}

