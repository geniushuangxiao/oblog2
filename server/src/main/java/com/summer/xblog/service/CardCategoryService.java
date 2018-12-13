package com.summer.xblog.service;

import com.summer.xblog.dao.CardCategoryRepository;
import com.summer.xblog.entity.CardCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CardCategoryService {
    @Autowired
    private CardCategoryRepository repository;

    /**
     * 将名字不重复的CardCategory存入数据库
     * @param category 卡片分组
     * @return 成功返回true
     */
    @Secured({ "ROLE_ADMIN" })
    public boolean save(CardCategory category) {
        boolean exists = repository.findByName(category.getName()).size() > 0;
        if(!exists) {
            repository.save(category);
        }
        return !exists;
    }

    public List<CardCategory> queryAll() {
        List<CardCategory> result = new ArrayList<>();
        repository.findAll().forEach(result::add);
        return result;
    }
}
