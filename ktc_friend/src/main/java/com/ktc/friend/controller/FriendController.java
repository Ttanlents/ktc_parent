package com.ktc.friend.controller;


import com.ktc.friend.pojo.Friend;
import com.ktc.friend.service.FriendService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author admin
 * @Description tb_friend 控制器层
 * @date 2020-11-27 09:55:45
 */
@RestController
@CrossOrigin
@RequestMapping("/friend")
public class FriendController {

    @Autowired
    private FriendService friendService;

    @Autowired
    HttpServletRequest request;

    /**
     * 查询全部数据
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public Result findAll() {
        return new Result(StatusCode.OK, true, "查询成功", friendService.findAll());
    }

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Result findById(@PathVariable String id) {
        return new Result(StatusCode.OK, true, "查询成功", friendService.findById(id));
    }

    /**
     * 分页+多条件查询
     *
     * @param searchMap 查询条件封装
     * @param page      页码
     * @param size      页大小
     * @return 分页结果
     */
    @RequestMapping(value = "/search/{page}/{size}", method = RequestMethod.POST)
    public Result findSearch(@RequestBody Map searchMap, @PathVariable int page, @PathVariable int size) {
        Page<Friend> pageList = friendService.findSearch(searchMap, page, size);
        return new Result(StatusCode.OK, true, "查询成功", new PageResult<Friend>(pageList.getTotalElements(), pageList.getContent()));
    }

    /**
     * 根据条件查询
     *
     * @param searchMap
     * @return
     */
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public Result findSearch(@RequestBody Map searchMap) {
        return new Result(StatusCode.OK, true, "查询成功", friendService.findSearch(searchMap));
    }

    /**
     * 增加
     *
     * @param friend
     */
    @RequestMapping(method = RequestMethod.POST)
    public Result add(@RequestBody Friend friend) {
        friendService.add(friend);
        return new Result(StatusCode.OK, true, "增加成功");
    }

    /**
     * 修改
     *
     * @param friend
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Result update(@RequestBody Friend friend, @PathVariable String id) {
        friend.setFriendid(id);
        friendService.update(friend);
        return new Result(StatusCode.OK, true, "修改成功");
    }

    /**
     * 删除
     *
     * @param id
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Result delete(@PathVariable String id) {
        friendService.deleteById(id);
        return new Result(StatusCode.OK, true, "删除成功");
    }

    /**
     * 关注  或  取消关注
     *
     * @param friendid
     */
    @RequestMapping(value = "like/{friendid}", method = RequestMethod.PUT)
    public Result like(@PathVariable String friendid) {
        Claims claims = (Claims) request.getAttribute("claims_user");
        String userId = claims.getId();
        Boolean flag = friendService.like(userId, friendid);
        if (flag){

            return new Result(StatusCode.OK,true,"加关注成功");
        }
        return new Result(StatusCode.OK,true,"取消关注成功");
    }
}
