package com.example.demo.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Result;
import com.example.demo.controller.dto.EnrollDegreeDto;
import com.example.demo.controller.dto.EnrollMajorDto;
import com.example.demo.entity.Enroll;
import com.example.demo.mapper.EnrollMapper;
import com.example.demo.service.EnrollService;
import com.example.demo.service.ServiceImpl.EnrollServiceImpl;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/enroll")
public class EnrollController extends BaseController {

    @Resource
    EnrollService enrollService = new EnrollServiceImpl();

    @PostMapping
    public Result<?> save(@RequestBody Enroll enroll) {
        enrollService.save(enroll);
        return Result.success();
    }

    @PutMapping
    public Result<?> update(@RequestBody Enroll enroll) {
        enrollService.update(enroll);
        return Result.success();
    }
    @GetMapping("/countDegree")
    public Result<?> countDegree() {

        return Result.success(enrollService.countDegree());
    }
    @GetMapping("/countMajor")
    public Result<?> countMajor() {

        return Result.success(enrollService.countMajor());
    }
    @GetMapping("/countState")
    public Result<?> countState() {

        return Result.success(enrollService.countState());
    }
    @GetMapping("/countSchool")
    public Result<?> countSchool() {

        return Result.success(enrollService.countSchool());
    }
    @DeleteMapping("/{id}")
    public Result<?> update(@PathVariable Long id) {
        enrollService.delete(id);
//        enrollMapper.deleteById(id);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<?> getById(@PathVariable Long id) {

        return Result.success(enrollService.getById(id));
    }

    @GetMapping
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search) {

        return Result.success(enrollService.findPage(pageNum,pageSize,search));
    }

    @GetMapping("/find1")
    public Result<?> findPage1(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search) {
        return Result.success(enrollService.findPage1(pageNum,pageSize,search));
    }
    @GetMapping("/find2")
    public Result<?> findPage2(@RequestParam(defaultValue = "1") Integer pageNum,
                               @RequestParam(defaultValue = "10") Integer pageSize,
                               @RequestParam(defaultValue = "") String search) {
        return Result.success(enrollService.findPage2(pageNum,pageSize,search));
    }
    @GetMapping(value = "/find3")
    public Result<?> findPage3(@RequestParam(defaultValue = "1") Integer pageNum,
                               @RequestParam(defaultValue = "10") Integer pageSize,
                               @RequestParam(defaultValue = "") String search) {
        return Result.success(enrollService.findPage3(pageNum,pageSize,search));
    }
    @GetMapping(value = "/findall")
    public Result<?> findPageall(@RequestParam(defaultValue = "1") Integer pageNum,
                               @RequestParam(defaultValue = "10") Integer pageSize,
                               @RequestParam(defaultValue = "") String search,
                               @RequestParam(defaultValue = "") String choice) {
        return Result.success(enrollService.findPageall(pageNum,pageSize,search,choice));
    }

    @GetMapping(value = "findBatch")
    public Result<?> findBatch(){
        return Result.success(enrollService.findBatch());
    }
}
