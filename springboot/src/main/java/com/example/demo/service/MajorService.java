package com.example.demo.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.entity.Major;

public interface MajorService {
    void delete(Long id);
    void save(Major major);
    void update(Major major);
    Major getById(Long id);
    Page<Major> findPage(Integer pageNum, Integer pageSize, String search);
}
