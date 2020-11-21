package com.ktc.base.services;

import com.ktc.base.dao.LabelDao;
import com.ktc.base.entity.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utils.IdWorker;

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
    public Label find(String id) {
        return labelDao.findById(id).get();

    }

}
