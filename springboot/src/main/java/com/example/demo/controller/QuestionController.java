package com.example.demo.controller;


import com.example.demo.common.Result;
import com.example.demo.mapper.QuestionMapper;
import com.example.demo.service.QuestionService;
import com.example.demo.service.ServiceImpl.QuestionServiceImpl;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;



@RestController
@RequestMapping("/question")
public class QuestionController {
    @Resource
    QuestionService questionService=new QuestionServiceImpl();

    @GetMapping
    public Result<?> findPage() {

        return Result.success(questionService.findPage());
    }

    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Long id) {

        questionService.delete(id);
        return Result.success();
    }


}
