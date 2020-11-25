package com.ktc.spit.service;

import com.ktc.spit.dao.SpitDao;
import com.ktc.spit.entity.Spit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import util.IdWorker;

import java.util.Date;
import java.util.List;

/**
 * @author 余俊锋
 * @date 2020/11/25 13:03
 * @Description
 */
@Service
public class SpitService {

    @Autowired
    private SpitDao spitDao;
    @Autowired
    private IdWorker idWorker;
    @Autowired
    MongoTemplate mongoTemplate;

    /**
     *如果评论  则  被评论的回复数+1
     * 否则就是吐槽   parentid 为空
    * @param spit
    */
    public void save(Spit spit) {
        spit.setId(idWorker.nextId() + "");
        spit.setPublishtime(new Date());//发布日期
        spit.setVisits(0);//浏览量
        spit.setShare(0);//分享数
        spit.setThumbup(0);//点赞数
        if (spit.getComment() == null) {
            spit.setComment(0);//回复数
        }
        if (spit.getParentid()!=null&&!"".equals(spit.getParentid())){
            //评论
            Query query=new Query();
            query.addCriteria(Criteria.where("_id").is(spit.getParentid()));
            Update update = new Update();
            update.inc("comment",1);
            mongoTemplate.updateFirst(query,update,"spit");
        }
        spitDao.save(spit);
    }

    public void delete(String id) {
        spitDao.deleteById(id);
    }

    public void update(Spit spit) {
        spitDao.save(spit);
    }

    public Spit findById(String id) {
      return  spitDao.findById(id).get();
    }

    public List<Spit> findAll() {
        return  spitDao.findAll();
    }

    public Page<Spit> getComment(String parentId, String page, String size) {
      return spitDao.findByParentid(parentId, PageRequest.of(Integer.parseInt(page)-1,Integer.parseInt(size)));
    }


    public void thumbup(String spitid) {
        /*Spit spit = spitDao.findById(spitid).get();
        Integer thumbup = spit.getThumbup();
        spit.setThumbup(thumbup+1);
        spitDao.save(spit);   效率低下*/
     //直接操作  mogo  不通过resposity
        Query query=new Query();
        query.addCriteria(Criteria.where("_id").is(spitid));
        Update update=new Update();
        update.inc("thumbup",1); //自增一
        mongoTemplate.updateFirst(query,update,"spit");
    }

    public void delthumbup(String spitid) {
        Query query=new Query();
        query.addCriteria(Criteria.where("_id").is(spitid));
        Update update=new Update();
        update.inc("thumbup",-1); //自增一
        mongoTemplate.updateFirst(query,update,"spit");
    }



}
