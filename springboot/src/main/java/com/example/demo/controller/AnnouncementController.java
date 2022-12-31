package com.example.demo.controller;


import com.example.demo.common.Result;
import com.example.demo.entity.Announcement;
import com.example.demo.service.AnnouncementService;
import com.example.demo.service.ServiceImpl.AnnouncementServiceImpl;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;

@RestController
@RequestMapping("/announcement")
public class AnnouncementController extends BaseController {


    @Resource
    AnnouncementService announcementService = new AnnouncementServiceImpl();

    @PostMapping
    public Result<?> save(@RequestBody Announcement announcement) {
        announcement.setTime(new Date());
        announcementService.save(announcement);
        return Result.success();
    }

    @PutMapping
    public Result<?> update(@RequestBody Announcement announcement) {
        announcementService.update(announcement);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Long id) {
        announcementService.delete(id);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<?> getById(@PathVariable Long id) {

        return Result.success(announcementService.getById(id));
    }

    @GetMapping
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search) {

        return Result.success(announcementService.findPage(pageNum,pageSize,search));
    }
}
