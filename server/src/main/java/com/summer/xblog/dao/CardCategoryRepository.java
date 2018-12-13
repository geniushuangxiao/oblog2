package com.summer.xblog.dao;

import com.summer.xblog.entity.CardCategory;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CardCategoryRepository extends CrudRepository<CardCategory, String> {
    List<CardCategory> findByName(String name);
}
