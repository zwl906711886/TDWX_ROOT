package com.example.demo.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Result;

import com.example.demo.entity.Major;
import com.example.demo.entity.Major;
import com.example.demo.entity.Major;
import com.example.demo.mapper.MajorMapper;
import com.example.demo.mapper.MajorMapper;
import com.example.demo.service.MajorService;
import com.example.demo.service.ServiceImpl.MajorServiceImpl;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/major")
public class MajorController extends BaseController {

    @Resource
    MajorService majorService= new MajorServiceImpl();

    @PostMapping
    public Result<?> save(@RequestBody Major Major) {
        majorService.save(Major);
        return Result.success();
    }

    @PutMapping
    public Result<?> update(@RequestBody Major Major) {
        majorService.update(Major);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Long id) {
        majorService.delete(id);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<?> getById(@PathVariable Long id) {

        return Result.success(majorService.getById(id));
    }


    @GetMapping
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search) {

        return Result.success(majorService.findPage(pageNum,pageSize,search));
    }

}
