package com.example.demo.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.controller.dto.EnrollDegreeDto;
import com.example.demo.controller.dto.EnrollMajorDto;
import com.example.demo.controller.dto.EnrollSchoolDto;
import com.example.demo.controller.dto.EnrollStateDto;
import com.example.demo.entity.Batch;
import com.example.demo.entity.Enroll;

import java.util.List;

public interface EnrollService {
    void save(Enroll enroll);
    void update(Enroll enroll);
    List<EnrollDegreeDto> countDegree();
    List<EnrollMajorDto> countMajor();
    List<EnrollStateDto> countState();
    List<EnrollSchoolDto> countSchool();
    void delete(Long id);
    Enroll getById(Long id);
    Page<Enroll> findPage(Integer pageNum,Integer pageSize,String search);
    Page<Enroll> findPage1(Integer pageNum,Integer pageSize,String search);
    Page<Enroll> findPage2(Integer pageNum,Integer pageSize,String search);
    Page<Enroll> findPage3(Integer pageNum,Integer pageSize,String search);
    Page<Enroll> findPageall(Integer pageNum,Integer pageSize,String search,String choice);
    List<Batch> findBatch();

}
