package com.kando.vo;

import lombok.Data;

@Data
public class PageVo {
    //页码
    private Integer page;
    //每页大小
    private Integer limit;
    //查询参数
    private String key;
}
