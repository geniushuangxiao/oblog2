package com.summer.xblog.dao;

import com.summer.xblog.entity.Card;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CardRepository extends CrudRepository<Card, Long> {
    List<Card> findByName(String name);
}
