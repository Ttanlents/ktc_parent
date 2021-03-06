package com.ktc.recruit.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.ktc.recruit.pojo.Enterprise;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description 企业 数据访问接口
 * @date 2020-11-23 13:45:13
*/
@Repository
public interface EnterpriseDao extends JpaRepository<Enterprise,String>,JpaSpecificationExecutor<Enterprise>{


    List<Enterprise> findTop9ByIshot(String ishost);
}

