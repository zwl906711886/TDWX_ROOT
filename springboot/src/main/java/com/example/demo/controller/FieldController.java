package com.example.demo.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Result;
import com.example.demo.entity.Field;
import com.example.demo.entity.Field;
import com.example.demo.mapper.FieldMapper;
import com.example.demo.service.FieldService;
import com.example.demo.service.ServiceImpl.FieldServiceImpl;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;

@RestController
@RequestMapping("/field")
public class FieldController extends BaseController {

    @Resource
    FieldService fieldService= new FieldServiceImpl();

    @PostMapping
    public Result<?> save(@RequestBody Field Field) {
        fieldService.save(Field);
        return Result.success();
    }

    @PutMapping
    public Result<?> update(@RequestBody Field Field) {
        fieldService.update(Field);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Long id) {
        fieldService.delete(id);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<?> getById(@PathVariable Long id) {

        return Result.success(fieldService.getById(id));
    }


    @GetMapping
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search) {

        return Result.success(fieldService.findPage(pageNum,pageSize,search));
    }
}