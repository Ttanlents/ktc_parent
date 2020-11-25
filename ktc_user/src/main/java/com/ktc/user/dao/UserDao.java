package com.ktc.user.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.ktc.user.pojo.User;

/**
 * @Description 用户 数据访问接口
 * @date 2020-11-25 17:56:08
*/
public interface UserDao extends JpaRepository<User,String>,JpaSpecificationExecutor<User>{

}

