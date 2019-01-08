package com.summer.xblog.category.dto;

import com.summer.xblog.entity.ConfigEntity;
import com.summer.xblog.tools.JsonUtils;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 分类，菜单使用
 */
@Data
public class Category {
    /**
     * 配置id
     */
    public static final String CATEGORY_ID = "cagetory_id";
    /**
     * 分类id
     */
    private String id;
    /**
     * 分类标签
     */
    private String label;
    /**
     * 子分类
     */
    private List<Category> children = new ArrayList<>();

    /**
     * 将category数组转成ConfigEntity
     * @param categories 分类数组
     * @return 配置Entity
     */
    public static Optional<ConfigEntity> toConfigEntity(List<Category> categories) {
        Optional<String> s = JsonUtils.toJson(categories);
        return s.map(json -> Optional.of(new ConfigEntity(CATEGORY_ID, json)))
                .orElse(Optional.empty());
    }
}
