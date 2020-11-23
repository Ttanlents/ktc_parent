package com.ktc.recruit.controller;

import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import com.ktc.recruit.pojo.Enterprise;
import com.ktc.recruit.service.EnterpriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
* @Description 企业 控制器层
* @author admin
* @date 2020-11-23 13:45:14
*/
@RestController
@CrossOrigin
@RequestMapping("/enterprise")
public class EnterpriseController{

@Autowired
private EnterpriseService enterpriseService;

/**
* 查询全部数据
* @return
*/
@RequestMapping(method= RequestMethod.GET)
public Result findAll(){
return new Result(StatusCode.OK,true,"查询成功",enterpriseService.findAll());
}

/**
* 根据ID查询
* @param id ID
* @return
*/
@RequestMapping(value="/{id}",method= RequestMethod.GET)
public Result findById(@PathVariable String id){
return new Result(StatusCode.OK,true,"查询成功",enterpriseService.findById(id));
}

/**
* 分页+多条件查询
* @param searchMap 查询条件封装
* @param page 页码
* @param size 页大小
* @return 分页结果
*/
@RequestMapping(value="/search/{page}/{size}",method=RequestMethod.POST)
public Result findSearch(@RequestBody Map searchMap , @PathVariable int page, @PathVariable int size){
Page<Enterprise> pageList = enterpriseService.findSearch(searchMap, page, size);
return  new Result(StatusCode.OK,true,"查询成功",  new PageResult<Enterprise>(pageList.getTotalElements(), pageList.getContent()) );
}

/**
* 根据条件查询
* @param searchMap
* @return
*/
@RequestMapping(value="/search",method = RequestMethod.POST)
public Result findSearch( @RequestBody Map searchMap){
return new Result(StatusCode.OK,true,"查询成功", enterpriseService.findSearch(searchMap));
}

/**
* 增加
* @param enterprise
*/
@RequestMapping(method=RequestMethod.POST)
public Result add(@RequestBody  Enterprise  enterprise  ){
enterpriseService.add( enterprise);
return new Result(StatusCode.OK,true,"增加成功");
}

/**
* 修改
* @param enterprise
*/
@RequestMapping(value="/{id}",method= RequestMethod.PUT)
public Result update(@RequestBody  Enterprise  enterprise, @PathVariable String id ){
enterprise.setId(id);
enterpriseService.update( enterprise);
return new Result(StatusCode.OK,true,"修改成功");
}

/**
* 删除
* @param id
*/
@RequestMapping(value="/{id}",method= RequestMethod.DELETE)
public Result delete(@PathVariable String id ){
enterpriseService.deleteById(id);
return new Result(StatusCode.OK,true,"删除成功");
}

/**
 *查询热门企业
* @param
*/
@RequestMapping(value="/search/hotlist",method= RequestMethod.GET)
public Result hotlist(){
    List<Enterprise> page=enterpriseService.hotlist();
    return new Result(StatusCode.OK,true,"查询成功",page);
}

}
