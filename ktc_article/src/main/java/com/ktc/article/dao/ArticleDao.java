package com.ktc.article.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.ktc.article.pojo.Article;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * @Description 文章 数据访问接口
 * @date 2020-11-23 19:51:53
*/
public interface ArticleDao extends JpaRepository<Article,String>,JpaSpecificationExecutor<Article>{

    @Modifying
    @Query("update Article article set article.state=1 where article.id=?1")
    void examine(String articleId);
    @Modifying
    @Query("update Article article set article.thumbup=article.thumbup+1 where article.id=?1")
    void thumbup(String articleId);
}

