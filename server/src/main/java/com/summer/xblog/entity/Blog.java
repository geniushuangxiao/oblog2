package com.summer.xblog.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    /**博客菜单路径*/
    @Column(length = 200)
    private String categoryId;
    /**博客名*/
    @Column(length = 200)
    private String name;
    /**博客内容*/
    @Column(length = 100000)
    private String content;
    /**博客描述*/
    @Column(length = 500)
    private String desc;
    /**博客是否发布标记*/
    private boolean release;
    /**作者*/
    @Column(length = 20)
    private String author;
    private long firstWriteTime;
    private long lastModifyTime;
    /**查看次数*/
    private long viewNum;
}
