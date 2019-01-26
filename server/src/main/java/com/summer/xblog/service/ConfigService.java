package com.summer.xblog.service;

import com.summer.xblog.category.dto.Category;
import com.summer.xblog.dao.ConfigReponsitory;
import com.summer.xblog.dto.CommonDTO;
import com.summer.xblog.entity.ConfigEntity;
import com.summer.xblog.tools.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
public class ConfigService {
    @Autowired
    private ConfigReponsitory configReponsitory;

    public CommonDTO saveCategory(List<Category> categories) {
        categories.forEach(item -> this.categoryPath(item, ""));
        Optional<ConfigEntity> optional = Category.toConfigEntity(categories);
        if(optional.isPresent()) {
            ConfigEntity save = configReponsitory.save(optional.get());
            return CommonDTO.success("配置分类成功", save);
        } else {
            return CommonDTO.fail("保存失败", categories);
        }
    }

    /**
     * 递归为树设置id路径
     * @param category 分类
     * @param idPath 父category的id路径
     */
    private void categoryPath(Category category, String idPath) {
        if(StringUtils.isEmpty(idPath)) {
            idPath = category.getId();
        } else {
            idPath = idPath + "." + category.getId();
        }
        category.setIdPath(idPath);
        for(Category item : category.getChildren()) {
            this.categoryPath(item, idPath);
        }
    }

    public CommonDTO queryCategory() {
        Optional<ConfigEntity> optional = configReponsitory.findById(Category.CATEGORY_ID);
        if(optional.isPresent()) {
            ConfigEntity category = optional.get();
            String content = category.getContent();
            Optional<List> list = JsonUtils.toObject(content, List.class);
            if(list.isPresent()) {
                return CommonDTO.success(list.get());
            }
        }
        return CommonDTO.fail("查询分类失败");
    }
}
