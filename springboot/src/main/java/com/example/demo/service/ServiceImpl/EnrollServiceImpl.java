package com.example.demo.service.ServiceImpl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.controller.dto.EnrollDegreeDto;
import com.example.demo.controller.dto.EnrollMajorDto;
import com.example.demo.controller.dto.EnrollSchoolDto;
import com.example.demo.controller.dto.EnrollStateDto;
import com.example.demo.entity.Batch;
import com.example.demo.entity.Enroll;
import com.example.demo.mapper.BatchMapper;
import com.example.demo.mapper.EnrollMapper;
import com.example.demo.service.EnrollService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class EnrollServiceImpl implements EnrollService {
    @Resource
    EnrollMapper enrollMapper;

    @Resource
    BatchMapper batchMapper;

    @Override
    public void save(Enroll enroll) {
        enrollMapper.insert(enroll);
    }

    @Override
    public void update(Enroll enroll) {
        enrollMapper.updateById(enroll);
    }

    @Override
    public List<EnrollDegreeDto> countDegree() {
        return enrollMapper.countDegree();
    }

    @Override
    public List<EnrollMajorDto> countMajor() {
        return enrollMapper.countMajor();
    }

    @Override
    public List<EnrollStateDto> countState() {
        return enrollMapper.countState();
    }

    @Override
    public List<EnrollSchoolDto> countSchool() {
        return enrollMapper.countSchool();
    }

    @Override
    public void delete(Long id) {
        enrollMapper.deleteById(id);
    }

    @Override
    public Enroll getById(Long id) {
         return enrollMapper.selectById(id);
    }

    @Override
    public Page<Enroll> findPage(Integer pageNum, Integer pageSize, String search) {
        LambdaQueryWrapper<Enroll> wrapper = Wrappers.lambdaQuery();
        if (StrUtil.isNotBlank(search)) {
            wrapper.like(Enroll::getName, search);
        }
        wrapper.eq(Enroll::getSubmitState, "已提交");
        return enrollMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
    }

    @Override
    public Page<Enroll> findPage1(Integer pageNum, Integer pageSize, String search) {
        LambdaQueryWrapper<Enroll> wrapper = Wrappers.lambdaQuery();
        if (StrUtil.isNotBlank(search)) {
            wrapper.like(Enroll::getName, search);
        }
        return enrollMapper.selectPage(new Page<>(pageNum, pageSize), wrapper.eq(Enroll::getBatch,1));
    }

    @Override
    public Page<Enroll> findPage2(Integer pageNum, Integer pageSize, String search) {
        LambdaQueryWrapper<Enroll> wrapper = Wrappers.lambdaQuery();
        if (StrUtil.isNotBlank(search)) {
            wrapper.like(Enroll::getName, search);
        }
        return enrollMapper.selectPage(new Page<>(pageNum, pageSize), wrapper.eq(Enroll::getBatch,2));

    }
    @Override
    public Page<Enroll> findPage3(Integer pageNum, Integer pageSize, String search) {
        LambdaQueryWrapper<Enroll> wrapper = Wrappers.lambdaQuery();
        if (StrUtil.isNotBlank(search)) {
            wrapper.like(Enroll::getName, search);
        }
        return enrollMapper.selectPage(new Page<>(pageNum, pageSize), wrapper.eq(Enroll::getBatch,3));
    }
    @Override
    public Page<Enroll> findPageall(Integer pageNum, Integer pageSize, String search,String choice) {
        LambdaQueryWrapper<Enroll> wrapper = Wrappers.lambdaQuery();
        if (StrUtil.isNotBlank(search)) {
            wrapper.like(Enroll::getName, search);
        }
        return enrollMapper.selectPage(new Page<>(pageNum, pageSize), wrapper.eq(Enroll::getBatch,choice));
    }

    @Override
    public List<Batch> findBatch() {
        QueryWrapper<Batch> wrapper = new QueryWrapper<>();
        wrapper.select("distinct batch_name");
        return batchMapper.selectList(wrapper);
    }

}
