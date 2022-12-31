package com.example.demo.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.entity.Field;

public interface FieldService {
    void delete(Long id);
    void save(Field field);
    void update(Field field);
    Field getById(Long id);
    Page<Field> findPage(Integer pageNum, Integer pageSize, String search);
}
