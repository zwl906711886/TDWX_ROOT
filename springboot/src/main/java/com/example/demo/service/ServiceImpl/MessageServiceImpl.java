package com.example.demo.service.ServiceImpl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.entity.Message;
import com.example.demo.entity.User;
import com.example.demo.mapper.MessageMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.MessageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class MessageServiceImpl implements MessageService {

    @Resource
    private MessageMapper messageMapper;
    @Resource
    private UserMapper userMapper;

    @Override
    public void save(Message Message, Long foreignId) {
        messageMapper.insert(Message);
    }

    @Override
    public int update(Message Message) {
        return messageMapper.updateById(Message);
    }

    @Override
    public void delete(Long id) {
        messageMapper.deleteById(id);
    }

    @Override
    public Message findById(Long id) {
        return messageMapper.selectById(id);
    }

    @Override
    public List<Message> findAll() {
        return messageMapper.selectList(null);
    }

    @Override
    public List<Message> foreign(Integer questionId) {
        return findByForeign(questionId);
    }

    @Override
    public List<Message> findByForeign(Integer questionId) {
        LambdaQueryWrapper<Message> queryWrapper = Wrappers.<Message>lambdaQuery().eq(Message::getForeignId, questionId).orderByDesc(Message::getId);
        List<Message> list = messageMapper.selectList(queryWrapper);
        for (Message Message : list) {
            User one = userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getUsername, Message.getUsername()));
            if (StrUtil.isNotBlank(one.getAvatar())) {
                Message.setAvatar(one.getAvatar());
            } else {
                // 默认一个头像
                Message.setAvatar("https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png");
            }
            Long parentId = Message.getParentId();
            list.stream().filter(c -> c.getId().equals(parentId)).findFirst().ifPresent(Message::setParentMessage);
        }
        return list;
    }

    @Override
    public Page<Message> findPage(String name, Integer pageNum, Integer pageSize) {
        LambdaQueryWrapper<Message> query = Wrappers.<Message>lambdaQuery().like(Message::getContent, name).orderByDesc(Message::getId);
        return messageMapper.selectPage(new Page<>(pageNum, pageSize), query);
//        return null;
    }
}
