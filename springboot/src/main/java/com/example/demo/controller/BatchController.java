package com.example.demo.controller;

import com.example.demo.common.Result;
import com.example.demo.entity.Batch;


import com.example.demo.service.BatchService;
import com.example.demo.service.ServiceImpl.BatchServiceImpl;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


@RestController
@RequestMapping("/batch")
public class BatchController extends BaseController {

    @Resource
    BatchService batchService= new BatchServiceImpl();

    @PostMapping
    public Result<?> save(@RequestBody Batch batch) {
        batchService.save(batch);
        return Result.success();
    }

    @PutMapping
    public Result<?> update(@RequestBody Batch batch) {
        batchService.update(batch);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Long id) {
        batchService.delete(id);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<?> getById(@PathVariable Long id) {

        return Result.success(batchService.getById(id));
    }


    @GetMapping
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search) {

        return Result.success(batchService.findPage(pageNum,pageSize,search));
    }


}
