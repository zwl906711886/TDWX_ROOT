package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@Data
@TableName("question")
public class Question {
    @TableId(type = IdType.AUTO)
    private Integer questionId;
    private String content;
    private Integer clickNum;
    private String time;
    private String owner;
}
