package com.example.demo.service.ServiceImpl;

import com.example.demo.entity.Question;
import com.example.demo.mapper.QuestionMapper;
import com.example.demo.service.QuestionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {
    @Resource
    QuestionMapper questionMapper;
    @Override
    public List<Question> findPage() {
        return questionMapper.selectList(null);
    }

    @Override
    public void delete(Long id) {
        questionMapper.deleteById(id);
    }
}
