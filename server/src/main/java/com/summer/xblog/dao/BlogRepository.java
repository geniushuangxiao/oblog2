package com.summer.xblog.dao;

import com.summer.xblog.entity.Blog;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BlogRepository extends CrudRepository<Blog, Long> {
    List<Blog> findByCard(String name);
}
