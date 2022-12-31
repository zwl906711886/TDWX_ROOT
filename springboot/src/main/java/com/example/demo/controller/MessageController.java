package com.example.demo.controller;

import cn.hutool.core.date.DateUtil;
import com.example.demo.common.Result;
import com.example.demo.entity.Message;
import com.example.demo.service.MessageService;
import com.example.demo.service.ServiceImpl.MessageServiceImpl;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/message")
public class MessageController extends BaseController{
    @Resource
    MessageService messageService=new MessageServiceImpl();

    @PostMapping
    public Result<?> save(@RequestBody Message Message,@RequestParam Long foreignId) {
        Message.setUsername(getUser().getUsername());
        Message.setTime(DateUtil.formatDateTime(new Date()));
        Message.setForeignId(foreignId);
        messageService.save(Message,foreignId);
        return Result.success();
    }

    @PutMapping
    public Result<?> update(@RequestBody Message Message) {

        return Result.success(messageService.update(Message));
    }

    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Long id) {
        messageService.delete(id);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<?> findById(@PathVariable Long id) {
        return Result.success(messageService.findById(id));
    }

    @GetMapping
    public Result<?> findAll() {

        return Result.success(messageService.findAll());
    }

    @GetMapping("/foreign")
    public Result<?> foreign(@RequestParam() Integer questionId) {

        return Result.success(messageService.foreign(questionId));
    }

    @GetMapping("/page")
    public Result<?> findPage(@RequestParam(required = false, defaultValue = "") String name,
                                                @RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                                @RequestParam(required = false, defaultValue = "10") Integer pageSize) {

        return Result.success(messageService.findPage(name,pageNum,pageSize));
    }

    private List<Message> findByForeign(Integer questionId) {
        return messageService.findByForeign(questionId);
    }

}
