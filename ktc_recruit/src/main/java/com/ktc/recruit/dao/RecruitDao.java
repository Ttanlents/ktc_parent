package com.ktc.recruit.dao;

import com.ktc.recruit.pojo.Recruit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @Description 职位 数据访问接口
 * @date 2020-11-23 13:48:45
*/
public interface RecruitDao extends JpaRepository<Recruit,String>,JpaSpecificationExecutor<Recruit>{
    List<Recruit> findTop4ByStateOrderByCreatetimeDesc(String state); //1推荐
    List<Recruit> findTop12ByStateNotOrderByCreatetimeDesc(String state); //0关闭
}

