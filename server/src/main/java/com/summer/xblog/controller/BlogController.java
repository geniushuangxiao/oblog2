package com.summer.xblog.controller;

import com.summer.xblog.dao.BlogRepository;
import com.summer.xblog.dto.CommonDTO;
import com.summer.xblog.entity.Blog;
import com.summer.xblog.service.BlogService;
import com.summer.xblog.service.CardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/blog")
public class BlogController {
    private static final Logger log = LoggerFactory.getLogger(BlogController.class);
    @Autowired
    private BlogRepository repository;
    @Autowired
    private BlogService service;
    @Autowired
    private CardService cardService;
    @GetMapping("/card/{card}")
    public List<Blog> queryByCategory(@PathVariable(name="card") String card) {
        //暂时不使用缓存，后续负载大后，使用缓存
        cardService.viewTimeRefresh(card);
        return repository.findByCard(card);
    }
    @GetMapping("/{id}")
    public Blog queryById(@PathVariable(name="id") long id) {
        final Optional<Blog> optional = repository.findById(id);
        service.viewTimeRefresh(id);
        return optional.isPresent()? optional.get() : null;
    }
    @PostMapping
    public CommonDTO save(Principal principal, @RequestBody Blog blog) {
        return service.save(principal, blog);
    }
    @DeleteMapping
    public void delete(Principal principal, @RequestParam long id) {
        service.deleteById(principal, id);
    }
}
