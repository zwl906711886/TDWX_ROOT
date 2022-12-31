package com.example.demo.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.entity.Announcement;


public interface AnnouncementService {

    void delete(Long id);
    void save(Announcement announcement);
    void update(Announcement announcement);
    Announcement getById(Long id);
    Page<Announcement> findPage(Integer pageNum, Integer pageSize, String search);
}
