package com.summer.xblog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 存储系统配置的entity
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConfigEntity {
    /**
     * 配置id
     */
    @Id
    private String id;

    /**
     * 配置内容
     */
    @Column(length = 1024 * 1024)
    private String content;
}
