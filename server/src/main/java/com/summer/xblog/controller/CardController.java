package com.summer.xblog.controller;

import com.summer.xblog.dao.CardRepository;
import com.summer.xblog.entity.Card;
import com.summer.xblog.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/card")
public class CardController {
    @Autowired
    private CardRepository repository;
    @Autowired
    private CardService service;
    @GetMapping
    public List<Card> query() {
        List<Card> result = new ArrayList<>();
        repository.findAll().forEach(result::add);
        return result;
    }
    @PostMapping
    public void save(@RequestBody List<Card> cards) {
        service.save(cards);
    }
}
