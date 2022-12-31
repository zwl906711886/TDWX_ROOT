package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;


@Data
@TableName("field")
public class Field {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String fieldName;
    private String fieldState;
}
