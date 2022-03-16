package com.xxs.graduationproject.sys.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

@Data
public class MyPage {
    @TableField(exist = false)
    private Integer page;//查询当前页

    @TableField(exist = false)
    private  Integer row;//查询行数
}
