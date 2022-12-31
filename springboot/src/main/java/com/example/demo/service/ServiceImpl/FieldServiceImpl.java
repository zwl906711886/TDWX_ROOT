package com.example.demo.service.ServiceImpl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.entity.Field;
import com.example.demo.mapper.FieldMapper;
import com.example.demo.service.FieldService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class FieldServiceImpl implements FieldService {
    @Resource
    FieldMapper fieldMapper;

    @Override
    public void delete(Long id) {
        fieldMapper.deleteById(id);
    }

    @Override
    public void save(Field Field) {
        fieldMapper.insert(Field);
    }

    @Override
    public void update(Field Field) {
        fieldMapper.updateById(Field);
    }


    @Override
    public Field getById(Long id) {
        return fieldMapper.selectById(id);
    }

    @Override
    public Page<Field> findPage(Integer pageNum, Integer pageSize, String search) {
        LambdaQueryWrapper<Field> wrapper = Wrappers.lambdaQuery();
        if (StrUtil.isNotBlank(search)) {
            wrapper.like(Field::getId, search);
        }
        return fieldMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);

    }
}
