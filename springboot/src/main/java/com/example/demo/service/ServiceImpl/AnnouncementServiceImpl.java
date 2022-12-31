package com.example.demo.service.ServiceImpl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.entity.Announcement;
import com.example.demo.mapper.AnnouncementMapper;
import com.example.demo.service.AnnouncementService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class AnnouncementServiceImpl implements AnnouncementService {
    @Resource
    AnnouncementMapper announcementMapper;

    @Override
    public void delete(Long id) {

        announcementMapper.deleteById(id);
    }

    @Override
    public void save(Announcement announcement) {
        announcementMapper.insert(announcement);
    }

    @Override
    public void update(Announcement announcement) {
        announcementMapper.updateById(announcement);
    }

    @Override
    public Announcement getById(Long id) {
        return announcementMapper.selectById(id);
    }

    @Override
    public Page<Announcement> findPage(Integer pageNum, Integer pageSize, String search) {
        LambdaQueryWrapper<Announcement> wrapper = Wrappers.lambdaQuery();
        if (StrUtil.isNotBlank(search)) {
            wrapper.like(Announcement::getTitle, search);
        }
        return announcementMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
    }
}
