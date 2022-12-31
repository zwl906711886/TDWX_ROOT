package com.example.demo.service;

import com.example.demo.entity.Question;

import java.util.List;


public interface QuestionService {

    List<Question> findPage();
    void delete(Long id);
}
