package com.ktc.qa.dao;

import com.ktc.qa.pojo.Problem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

/**
 * @Description 问题 数据访问接口
 * @date 2020-11-23 18:13:03
*/
public interface ProblemDao extends JpaRepository<Problem,String>,JpaSpecificationExecutor<Problem>{

    @Query("select p from Problem p where p.id in (select pl.problemId from Pl pl where pl.labelId=?1)")
    Page<Problem> findByLabelIdOrderByReplytime(String labelid, Pageable of);

    @Query("select p from Problem p where p.id in (select pl.problemId from Pl pl where pl.labelId=?1)")
    Page<Problem> findByLabelIdOrderByReply(String labelid, Pageable reply);

    @Query("select p from Problem p where p.id in (select pl.problemId from Pl pl where pl.labelId=?1) and p.reply=0")
    Page<Problem> findByLabelId(String labelid,Pageable reply);
}

