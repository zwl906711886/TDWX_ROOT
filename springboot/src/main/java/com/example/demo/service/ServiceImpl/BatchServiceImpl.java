package com.example.demo.service.ServiceImpl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.entity.Batch;
import com.example.demo.mapper.BatchMapper;
import com.example.demo.service.BatchService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class BatchServiceImpl implements BatchService {

    @Resource
    BatchMapper batchMapper;

    @Override
    public void delete(Long id) {
        batchMapper.deleteById(id);
    }

    @Override
    public void save(Batch batch) {
        batchMapper.insert(batch);
    }

    @Override
    public void update(Batch batch) {
        batchMapper.updateById(batch);
    }


    @Override
    public Batch getById(Long id) {
        return batchMapper.selectById(id);
    }

    @Override
    public Page<Batch> findPage(Integer pageNum, Integer pageSize, String search) {
        LambdaQueryWrapper<Batch> wrapper = Wrappers.lambdaQuery();
        if (StrUtil.isNotBlank(search)) {
            wrapper.like(Batch::getBatchName, search);
        }
        return batchMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);

    }
}
