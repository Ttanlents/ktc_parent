package com.ktc.base.controller;

import entity.Label;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import com.ktc.base.services.LabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author 余俊锋
 * @date 2020/11/21 18:17
 * @Description
 */
@RestController
@RequestMapping("/label")
@CrossOrigin    //端口，协议，域名不同都是跨域
public class LabelController {
    @Autowired
    LabelService labelService;



     //添加
     @RequestMapping(method = RequestMethod.POST)
    public Result save(@RequestBody Label label) {
        labelService.save(label);
        return new Result(StatusCode.OK,true, "新增标签成功");
    }

    //删除

    @RequestMapping(value = "/{labelId}",method = RequestMethod.DELETE)
    public Result delete(@PathVariable String labelId) {
        labelService.delete(labelId);
        return new Result( StatusCode.OK,true,"删除成功");
    }

    /**
     *修改
    * @param labelId
    */
    @RequestMapping(value = "{labelId}",method = RequestMethod.PUT)
    public Result update(@RequestBody Label label,@PathVariable String labelId) {

        return new Result( StatusCode.OK,true,"新增标签成功");
    }


    /**
     *查询全部
    * @param
    */
    @RequestMapping(value = "list",method = RequestMethod.GET)
    @ResponseBody
    public Result list() {
        List<Label> list = labelService.findAll();
        Result result = new Result(StatusCode.OK,true,  "查询全部成功");
        result.setData(list);
        return result;
    }
    /**
     *根据id 查
    * @param id
    */
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Result list(@PathVariable String id) {
        Label label = labelService.findById(id);
        Result result = new Result(StatusCode.OK,true,  "查询成功");
        result.setData(label);
        return result;
    }

    /**
            *根据条件查询
     * @param serchMap
     */
    @RequestMapping(value = "/search",method = RequestMethod.POST)
    public Result search(@RequestBody Map serchMap) {
        List<Label> serch = labelService.serch(serchMap);
        Result result = new Result(StatusCode.OK,true,  "查询成功",serch);
        return result;
    }

    /**
     *根据条件查询+分页
     * @param serchMap
     */
    @RequestMapping(value = "/search/{page}/{size}",method = RequestMethod.POST)
    public Result findPage(@RequestBody Map serchMap,@PathVariable Integer page,@PathVariable  Integer size) {
        Page<Label> pageDate = labelService.findPage(serchMap,page,size);
        PageResult<Label> pageResult = new PageResult<>(pageDate.getTotalElements(),pageDate.getContent());
        Result result = new Result(StatusCode.OK,true,  "查询成功",pageResult);
        return result;
    }

}
