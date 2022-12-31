package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.controller.dto.EnrollDegreeDto;
import com.example.demo.controller.dto.EnrollMajorDto;
import com.example.demo.controller.dto.EnrollSchoolDto;
import com.example.demo.controller.dto.EnrollStateDto;
import com.example.demo.entity.Enroll;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface EnrollMapper extends BaseMapper<Enroll> {
    @Select("select count(name) count, degree from enroll GROUP BY degree")
    List<EnrollDegreeDto> countDegree();

    @Select("select count(name) count, major from enroll GROUP BY major")
    List<EnrollMajorDto> countMajor();

    @Select("select count(name) count, state from enroll GROUP BY state")
    List<EnrollStateDto> countState();

    @Select("select count(name) count, school from enroll GROUP BY school")
    List<EnrollSchoolDto> countSchool();

}
