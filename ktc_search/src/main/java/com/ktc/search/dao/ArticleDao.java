package com.ktc.search.dao;

import com.ktc.search.entity.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @author 余俊锋
 * @date 2020/11/24 19:43
 * @Description
 */
@Repository
public interface ArticleDao extends ElasticsearchRepository<Article,String> {

    public Page<Article> findByTitleLikeOrContentLike(String title, String content, Pageable pageable);
}
