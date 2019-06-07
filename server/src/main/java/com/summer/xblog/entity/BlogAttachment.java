package com.summer.xblog.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class BlogAttachment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    /**
     * 前端生成的唯一id
     */
    private String uid;

    /**
     * 附件名称
     */
    @Column(length = 100)
    private String name;
}
