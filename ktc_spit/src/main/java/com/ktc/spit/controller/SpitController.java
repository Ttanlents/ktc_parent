package com.ktc.spit.controller;

import com.ktc.spit.entity.Spit;
import com.ktc.spit.service.SpitService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.*;

/**
 * @author 余俊锋
 * @date 2020/11/25 12:59
 * @Description
 */

@RestController
@RequestMapping("/spit")
@CrossOrigin
public class SpitController {

    @Autowired
    private SpitService spitService;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 查询全部
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public Result findAll(){
        return new Result(StatusCode.OK,true,"查询成功",spitService.findAll());
    }

    /**
     * 根据id查询
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Result findById(@PathVariable String id){
        return new Result(StatusCode.OK,true,"查询成功",spitService.findById(id));
    }

    /**
     * 修改
     * @param spit
     * @return
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public Result update(@PathVariable String id,@RequestBody Spit spit){
        spit.setId(id);
        spitService.update(spit);
        return new Result(StatusCode.OK,true,"修改成功");
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public Result delete(@PathVariable String id){
        spitService.delete(id);
        return new Result( StatusCode.OK,true,"删除成功");
    }

    /**
     * 增加
     * @param spit
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public Result add(@RequestBody Spit spit){
        spitService.save(spit);
        return new Result( StatusCode.OK,true,"添加成功");
    }

    @RequestMapping(value = "comment/{parentId}/{page}/{size}",method = RequestMethod.GET)
    public Result comment(@PathVariable String parentId,@PathVariable String page,@PathVariable String size){
        Page<Spit> comment = spitService.getComment(parentId, page, size);
        return new Result(StatusCode.OK,true,"查询成功",new PageResult<>(comment.getTotalElements(),comment.getContent()));
    }

    /**
     *为  评论吐槽 点赞+1
    * @param spitid
    */
    @RequestMapping(value = "thumbup/{spitid}",method = RequestMethod.GET)
    public Result comment(@PathVariable String spitid){
        String userId="3";
        ValueOperations ops = redisTemplate.opsForValue();
        Object value = ops.get("spit:" + userId + ":" + spitid);
        if (value==null){
            //我是来点赞的
            spitService.thumbup(spitid);
            redisTemplate.opsForValue().set("spit:"+userId+":"+spitid,true);
            return new Result(StatusCode.OK,true,"点赞成功");
        }
        spitService.delthumbup(spitid);
        redisTemplate.delete("spit:"+userId+":"+spitid);
        return new Result(StatusCode.OK,true,"取消点赞成功");
    }

}
