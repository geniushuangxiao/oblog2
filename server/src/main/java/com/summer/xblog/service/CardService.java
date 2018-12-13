package com.summer.xblog.service;

import com.summer.xblog.dao.CardCategoryRepository;
import com.summer.xblog.dao.CardRepository;
import com.summer.xblog.entity.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class CardService {
    @Autowired
    private CardRepository repository;
    @Autowired
    private CardCategoryRepository categoryRepository;
    @Secured({ "ROLE_ADMIN" })
    public void save(List<Card> cards) {
        //卡片存在
        cards.forEach(card -> {
            if(0 != card.getId()) {
                Optional<Card> byId = repository.findById(card.getId());
                if(byId.isPresent()) {
                    Card saved = byId.get();
                    saved.setName(card.getName());
                    saved.setDesc(card.getDesc());
                    repository.save(saved);
                }
            }
            //卡片不存在
            if(!categoryRepository.findByName(card.getCategory()).isEmpty()) {
                repository.save(card.erase());
            }
        });
    }
    public void viewTimeRefresh(String cardName) {
        if(null == cardName || cardName.isEmpty()) {
            return;
        }
        List<Card> cards = repository.findByName(cardName);
        if(!cards.isEmpty()) {
            Card card = cards.get(0);
            card.setView(card.getView() + 1);
            repository.save(card);
        }
    }
}
