package com.summer.xblog.cache;

import com.summer.xblog.dao.CardRepository;
import com.summer.xblog.entity.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CardViewTimeCache {
    @Autowired
    private CardRepository repository;
    private Map<String, Long> cache = new HashMap<>();
    public synchronized void cache(String cardName) {
        if(null == cardName || cardName.equals("")) {
            return;
        }
        Long viewTime = cache.get(cardName);
        if(null != viewTime) {
            cache.put(cardName, viewTime + 1);
            return;
        }
        List<Card> cards = repository.findByName(cardName);
        if(!cards.isEmpty()) {
            Card card = cards.get(0);
            cache.put(card.getName(), card.getView() + 1);
        }
    }
    public synchronized void save() {
        cache.keySet().forEach(name -> {
            List<Card> cards = repository.findByName(name);
            if(!cards.isEmpty()) {
                Card card = cards.get(0);
                card.setView(cache.get(name));
                repository.save(card);
            } else {
                cache.remove(name);
            }
        });
    }
}
