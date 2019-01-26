package com.summer.xblog.controller;

import com.summer.xblog.dao.BlogRepository;
import com.summer.xblog.dto.CommonDTO;
import com.summer.xblog.entity.Blog;
import com.summer.xblog.service.BlogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/blog")
@Slf4j
public class BlogController {
    @Autowired
    private BlogRepository repository;
    @Autowired
    private BlogService service;
    @GetMapping("/card/{card}")
    public CommonDTO<List<Blog>> queryByCategory(@PathVariable(name="card") String card) {
        List<Blog> blogs = repository.findByCard(card);
        return CommonDTO.success(blogs);
    }
    @GetMapping("/{id}")
    public CommonDTO<Blog> queryById(@PathVariable(name="id") long id) {
        final Optional<Blog> optional = repository.findById(id);
        service.viewTimeRefresh(id);
        return optional.map(CommonDTO::success)
                .orElseGet(() -> CommonDTO.fail(String.format("未找到id为%d的博客", id)));
    }
    @PostMapping
    public CommonDTO save(Principal principal, @RequestBody Blog blog) {
        return service.save(principal, blog);
    }
    @DeleteMapping
    public CommonDTO delete(Principal principal, @RequestParam long id) {
        boolean success = service.deleteById(principal, id);
        if(success) {
            return CommonDTO.success(String.format("删除id为%d的博客成功", id));
        } else {
            return CommonDTO.fail(String.format("删除id为%d的博客失败", id));
        }
    }
}
