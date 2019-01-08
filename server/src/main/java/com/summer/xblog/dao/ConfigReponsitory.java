package com.summer.xblog.dao;

import com.summer.xblog.entity.ConfigEntity;
import org.springframework.data.repository.CrudRepository;

public interface ConfigReponsitory extends CrudRepository<ConfigEntity, String> {
}
