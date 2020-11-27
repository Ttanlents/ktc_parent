package com.ktc.friend.controller;

import com.ktc.friend.pojo.NoFriend;
import com.ktc.friend.service.NoFriendService;
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
 * @Description tb_nofriend 控制器层
 * @date 2020-11-27 11:11:17
 */
@RestController
@CrossOrigin
@RequestMapping("/noFriend")
public class NoFriendController {

    @Autowired
    private NoFriendService noFriendService;

    @Autowired
    private HttpServletRequest request;

    /**
     * 查询全部数据
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public Result findAll() {
        return new Result(StatusCode.OK, true, "查询成功", noFriendService.findAll());
    }

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Result findById(@PathVariable String id) {
        return new Result(StatusCode.OK, true, "查询成功", noFriendService.findById(id));
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
        Page<NoFriend> pageList = noFriendService.findSearch(searchMap, page, size);
        return new Result(StatusCode.OK, true, "查询成功", new PageResult<NoFriend>(pageList.getTotalElements(), pageList.getContent()));
    }

    /**
     * 根据条件查询
     *
     * @param searchMap
     * @return
     */
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public Result findSearch(@RequestBody Map searchMap) {
        return new Result(StatusCode.OK, true, "查询成功", noFriendService.findSearch(searchMap));
    }

    /**
     * 增加
     *
     * @param noFriend
     */
    @RequestMapping(method = RequestMethod.POST)
    public Result add(@RequestBody NoFriend noFriend) {
        noFriendService.add(noFriend);
        return new Result(StatusCode.OK, true, "增加成功");
    }

    /**
     * 修改
     *
     * @param noFriend
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Result update(@RequestBody NoFriend noFriend, @PathVariable String id) {
        noFriend.setFriendid(id);
        noFriendService.update(noFriend);
        return new Result(StatusCode.OK, true, "修改成功");
    }

    /**
     * 删除
     *
     * @param id
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Result delete(@PathVariable String id) {
        noFriendService.deleteById(id);
        return new Result(StatusCode.OK, true, "删除成功");
    }

    /**
     * 删除好友(拉黑)
     *
     * @param friendid
     * @return
     */
    @RequestMapping(value = "/{friendid}", method = RequestMethod.DELETE)
    public Result deleteFriend(@PathVariable String friendid) {
        Claims claims = (Claims) request.getAttribute("claims_user");

        if (claims == null) {
            return new Result( StatusCode.ERROR,false, "请先登录");
        }

        String userid = claims.getId();

        Boolean flag = noFriendService.addNoFriend(userid, friendid);

        if (flag) {
            return new Result(StatusCode.OK,true,  "拉黑成功");
        } else {
            return new Result( StatusCode.OK,true,  "您已经拉黑啦！");
        }
    }

}
