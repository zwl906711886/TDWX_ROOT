package com.example.demo.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
@TableName("enroll")
@Data
public class Enroll {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private String school;
    private String major;
    private String degree;
    private String state;
    private String batch;
    private String submitState;
}
