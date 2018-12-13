package com.summer.xblog.controller;

import com.summer.xblog.entity.CardCategory;
import com.summer.xblog.service.CardCategoryService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@RestController
@RequestMapping("/api/card/category")
public class CardCategoryController {
    @Autowired
    private CardCategoryService cardCategoryService;
    @ApiOperation("查询所有卡片分组")
    @GetMapping
    public List<CardCategory> query() {
        return cardCategoryService.queryAll();
    }
    @ApiOperation("添加卡片分组")
    @PostMapping
    public List<CardCategory> save(@RequestBody  List<CardCategory> categories) {
        List<CardCategory> failed = new ArrayList<>();
        categories.forEach((category)-> {
            boolean success = cardCategoryService.save(category);
            if(!success) {
                failed.add(category);
            }
        });
        return failed;
    }
}
