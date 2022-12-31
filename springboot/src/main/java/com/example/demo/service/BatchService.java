package com.example.demo.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.entity.Batch;

public interface BatchService {

    void delete(Long id);
    void save(Batch batch);
    void update(Batch batch);
    Batch getById(Long id);
    Page<Batch> findPage(Integer pageNum, Integer pageSize, String search);
}
