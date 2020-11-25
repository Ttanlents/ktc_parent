package com.ktc.spit.dao;

import com.ktc.spit.entity.Spit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author 余俊锋
 * @date 2020/11/25 13:00
 * @Description
 */
public interface SpitDao extends MongoRepository<Spit,String> {
    Page<Spit> findByParentid(String parentId, Pageable of);
}
