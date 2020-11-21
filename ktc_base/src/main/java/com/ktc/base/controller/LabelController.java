package com.ktc.base.controller;

import com.ktc.base.entity.Label;
import com.ktc.base.entity.Result;
import com.ktc.base.entity.StatusCode;
import com.ktc.base.services.LabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 余俊锋
 * @date 2020/11/21 18:17
 * @Description
 */
@Controller
@RequestMapping("label")
public class LabelController {
    @Autowired
    LabelService labelService;

    @RequestMapping("save")
    public Result save(@RequestBody Label label) {
        labelService.save(label);
        return new Result(true, StatusCode.OK,"新增标签成功");
    }
}
