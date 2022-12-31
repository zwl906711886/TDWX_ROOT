package com.example.demo.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.entity.News;

public interface NewsService {
    void delete(Long id);
    void save(News news);
    void update(News news);
    News getById(Long id);
    Page<News> findPage(Integer pageNum, Integer pageSize, String search);
}
