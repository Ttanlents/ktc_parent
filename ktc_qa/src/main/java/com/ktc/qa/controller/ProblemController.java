package com.ktc.qa.controller;

import entity.PageResult;
import com.ktc.qa.client.LabelClient;
import com.ktc.qa.pojo.Problem;
import com.ktc.qa.service.ProblemService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author admin
 * @Description 问题 控制器层
 * @date 2020-11-23 18:13:04
 */
@RestController
@CrossOrigin
@RequestMapping("/problem")
public class ProblemController {

    @Autowired
    private ProblemService problemService;

    /**
     * 查询全部数据
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public Result findAll() {
        return new Result(StatusCode.OK, true, "查询成功", problemService.findAll());
    }

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Result findById(@PathVariable String id) {
        return new Result(StatusCode.OK, true, "查询成功", problemService.findById(id));
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
        Page<Problem> pageList = problemService.findSearch(searchMap, page, size);
        return new Result(StatusCode.OK, true, "查询成功", new PageResult<Problem>(pageList.getTotalElements(), pageList.getContent()));
    }

    /**
     * 根据条件查询
     *
     * @param searchMap
     * @return
     */
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public Result findSearch(@RequestBody Map searchMap) {
        return new Result(StatusCode.OK, true, "查询成功", problemService.findSearch(searchMap));
    }

    /**
     * 增加
     *
     * @param problem
     */
    @RequestMapping(method = RequestMethod.POST)
    public Result add(@RequestBody Problem problem) {
        problemService.add(problem);
        return new Result(StatusCode.OK, true, "增加成功");
    }

    /**
     * 修改
     *
     * @param problem
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Result update(@RequestBody Problem problem, @PathVariable String id) {
        problem.setId(id);
        problemService.update(problem);
        return new Result(StatusCode.OK, true, "修改成功");
    }

    /**
     * 删除
     *
     * @param id
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Result delete(@PathVariable String id) {
        problemService.deleteById(id);
        return new Result(StatusCode.OK, true, "删除成功");
    }

    /**
     * 查询最新问题列表
     *
     * @param
     */
    @RequestMapping(value = "/newlist/{labelid}/{page}/{size}", method = RequestMethod.GET)
    public Result newlist(@PathVariable String labelid,@PathVariable Integer page,@PathVariable Integer size) {
        Page<Problem> pageDate=  problemService.newlist(labelid,page,size);
        return new Result(StatusCode.OK, true, "查询问题列表成功！",new PageResult<>(pageDate.getTotalElements(),pageDate.getContent()));
    }

    /**
     * 查询热门问题列表
     *
     * @param
     */
    @RequestMapping(value = "/hotlist/{labelid}/{page}/{size}", method = RequestMethod.GET)
    public Result hotlist(@PathVariable String labelid,@PathVariable Integer page,@PathVariable Integer size) {
        Page<Problem> pageDate=  problemService.hotlist(labelid,page,size);
        return new Result(StatusCode.OK, true, "查询热门问题列表成功！",new PageResult<>(pageDate.getTotalElements(),pageDate.getContent()));
    }
    /**
     * 等待回复
     *
     * @param
     */
    @RequestMapping(value = "/waitlist/{labelid}/{page}/{size}", method = RequestMethod.GET)
    public Result waitlist(@PathVariable String labelid,@PathVariable Integer page,@PathVariable Integer size) {
        Page<Problem> pageDate=  problemService.waitlist(labelid,page,size);
        return new Result(StatusCode.OK, true, "查询热门问题列表成功！",new PageResult<>(pageDate.getTotalElements(),pageDate.getContent()));
    }

    @Autowired
    private LabelClient labelClient;
    /**
     *服务调用
    * @param labelid
    */
    @RequestMapping(value = "/label/{labelid}",method = RequestMethod.GET)
    public Result findLabelByLabelId(@PathVariable String labelid){

        Result result = labelClient.findById(labelid);

        return result;
    }



}
