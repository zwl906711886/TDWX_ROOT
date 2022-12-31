package com.example.demo.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Result;
import com.example.demo.entity.News;
import com.example.demo.entity.News;
import com.example.demo.mapper.NewsMapper;
import com.example.demo.service.NewsService;
import com.example.demo.service.ServiceImpl.NewsServiceImpl;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;

@RestController
@RequestMapping("/news")
public class NewsController extends BaseController {

    @Resource
    NewsService newsService= new NewsServiceImpl();

    @PostMapping
    public Result<?> save(@RequestBody News News) {
        newsService.save(News);
        return Result.success();
    }

    @PutMapping
    public Result<?> update(@RequestBody News News) {
        newsService.update(News);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Long id) {
        newsService.delete(id);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<?> getById(@PathVariable Long id) {

        return Result.success(newsService.getById(id));
    }


    @GetMapping
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search) {

        return Result.success(newsService.findPage(pageNum,pageSize,search));
    }
}
