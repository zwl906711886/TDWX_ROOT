package com.example.demo.service.ServiceImpl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.entity.Major;
import com.example.demo.mapper.MajorMapper;
import com.example.demo.service.MajorService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class MajorServiceImpl implements MajorService {
    @Resource
    MajorMapper majorMapper;

    @Override
    public void delete(Long id) {
        majorMapper.deleteById(id);
    }

    @Override
    public void save(Major Major) {
        majorMapper.insert(Major);
    }

    @Override
    public void update(Major Major) {
        majorMapper.updateById(Major);
    }


    @Override
    public Major getById(Long id) {
        return majorMapper.selectById(id);
    }

    @Override
    public Page<Major> findPage(Integer pageNum, Integer pageSize, String search) {
        System.out.println(search);
        LambdaQueryWrapper<Major> wrapper = Wrappers.lambdaQuery();
        if (StrUtil.isNotBlank(search)) {
            wrapper.like(Major::getMajor, search);
        }
        return majorMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);

    }
}
