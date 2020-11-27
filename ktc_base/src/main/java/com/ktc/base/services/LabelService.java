package com.ktc.base.services;

import com.ktc.base.dao.LabelDao;
import com.ktc.base.entity.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import util.IdWorker;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author 余俊锋
 * @date 2020/11/21 18:02
 * @Description
 */
@Service
public class LabelService {

    @Autowired
    private LabelDao labelDao;

    @Autowired
    IdWorker idWorker;

    //增
    public void save(Label label) {
        label.setId(idWorker.nextId() + "");
        labelDao.save(label);
    }

    //删除
    public void delete(String id) {
        labelDao.deleteById(id);
    }

    //改
    public void update(Label label) {
        labelDao.save(label);
    }

    //查
    public Label findById(String id) {
        return labelDao.findById(id).get();

    }

    //查
    public List<Label> findAll() {
        return labelDao.findAll();

    }

    private Specification<Label> createSepc(Map serchMap) {
        List<Predicate> list = new ArrayList<>();
        Specification<Label> specification = new Specification<Label>() {
            @Override
            public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                if (serchMap.get("id") != null && !"".equals(serchMap.get("id"))) {
                    Expression<String> id = root.get("id").as(String.class);
                    Predicate predicate = cb.equal(id, serchMap.get("id") + "");
                    list.add(predicate);
                }
                if (serchMap.get("labelname") != null && !"".equals(serchMap.get("labelname"))) {
                    Predicate predicate = cb.like(root.get("labelname").as(String.class), "%"+serchMap.get("labelname") + "%");
                    list.add(predicate);
                }
                if (serchMap.get("count") != null && !"".equals(serchMap.get("count"))) {
                    Predicate predicate = cb.equal(root.get("count").as(Integer.class), serchMap.get("count"));
                    list.add(predicate);
                }

                if (serchMap.get("state") != null && !"".equals(serchMap.get("state"))) {
                    Predicate predicate = cb.equal(root.get("state").as(String.class), serchMap.get("state"));
                    list.add(predicate);
                }
                if (serchMap.get("recommend") != null && !"".equals(serchMap.get("recommend"))) {
                    Predicate predicate = cb.equal(root.get("recommend").as(String.class), serchMap.get("recommend"));
                    list.add(predicate);
                }

                Predicate[] predicates = new Predicate[list.size()];
                return cb.and(list.toArray(predicates));

            }
        };
        return specification;
    }


    /**
     * 动态条件查询
     *
     * @param serchMap
     */
    public List<Label> serch(Map serchMap) {
        Specification<Label> sepc = createSepc(serchMap);
        return labelDao.findAll(sepc);
    }

    public Page<Label> findPage(Map serchMap,Integer page,Integer size){
        Specification<Label> sepc = this.createSepc(serchMap);
     // labelDao.findAll(sepc, PageRequest.of(page, size, Sort.by(Sort.Direction.DESC,"id")));
      return   labelDao.findAll(sepc, PageRequest.of(page-1, size));
    }

}
