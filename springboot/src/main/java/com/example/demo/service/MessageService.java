package com.example.demo.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.entity.Message;
import io.swagger.models.auth.In;

import java.util.List;

public interface MessageService {
    void save(Message Message,Long foreignId);
    int update(Message Message);
    void delete(Long id);
    Message findById(Long id);
    List<Message> findAll();
    List<Message> foreign(Integer questionId);
    List<Message> findByForeign(Integer questionId);
    Page<Message> findPage(String name,Integer pageNum,Integer pageSize);
}
