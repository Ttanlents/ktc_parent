package com.ktc.search.controller;

import com.ktc.search.entity.Article;
import com.ktc.search.service.ArticleService;
import com.ktc.base.entity.PageResult;
import com.ktc.base.entity.Result;
import com.ktc.base.entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

/**
 * @author 余俊锋
 * @date 2020/11/24 19:54
 * @Description
 */
@RestController
@RequestMapping("article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @RequestMapping(method = RequestMethod.POST)
    public Result save(@RequestBody  Article article){
        articleService.saveArticle(article);
        return new Result(StatusCode.OK,true,"新增成功");
    }

    @RequestMapping(value = "/search/{keywords}/{page}/{size}", method = RequestMethod.GET)
    public Result search(@PathVariable String keywords, @PathVariable int page, @PathVariable int size) {
        Page<Article> pageData=articleService.search(keywords,page,size);

        return new Result(StatusCode.OK,true,"查询成功",new PageResult<>(pageData.getTotalElements(),pageData.getContent()));

    }

}
