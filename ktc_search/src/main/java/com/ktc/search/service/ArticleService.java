package com.ktc.search.service;

import com.ktc.search.dao.ArticleDao;
import com.ktc.search.entity.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import util.IdWorker;

/**
 * @author 余俊锋
 * @date 2020/11/24 19:45
 * @Description
 */
@Service
public class ArticleService {
    @Autowired
    ArticleDao articleDao;
    @Autowired
    IdWorker idWorker;


    public void saveArticle(Article article){
      article.setId(idWorker.nextId()+"");
      articleDao.save(article);
    }

    public Page<Article> search(String keywords, int page, int size) {
      return   articleDao.findByTitleLikeOrContentLike(keywords,keywords, PageRequest.of(page-1,size));
    }
}
