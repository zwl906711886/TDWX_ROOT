package com.example.demo.service.ServiceImpl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.entity.News;
import com.example.demo.mapper.NewsMapper;
import com.example.demo.service.NewsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class NewsServiceImpl implements NewsService {
    @Resource
    NewsMapper newsMapper;

    @Override
    public void delete(Long id) {
        newsMapper.deleteById(id);
    }

    @Override
    public void save(News News) {
        newsMapper.insert(News);
    }

    @Override
    public void update(News News) {
        newsMapper.updateById(News);
    }


    @Override
    public News getById(Long id) {
        return newsMapper.selectById(id);
    }

    @Override
    public Page<News> findPage(Integer pageNum, Integer pageSize, String search) {
        System.out.println(search);
        LambdaQueryWrapper<News> wrapper = Wrappers.lambdaQuery();
        if (StrUtil.isNotBlank(search)) {
            wrapper.like(News::getTitle, search);
        }
        return newsMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);

    }
}
