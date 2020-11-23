package com.ktc.base.dao;

import entity.Label;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author 余俊锋
 * @date 2020/11/21 17:59
 * @Description
 */

public interface LabelDao extends JpaRepository<Label,String>, JpaSpecificationExecutor<Label> {


}
