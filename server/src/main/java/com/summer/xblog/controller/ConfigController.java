package com.summer.xblog.controller;

import com.summer.xblog.category.dto.Category;
import com.summer.xblog.dto.CommonDTO;
import com.summer.xblog.service.ConfigService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/config")
@Slf4j
public class ConfigController {
    @Autowired
    private ConfigService configService;

    @PostMapping("/category")
    public CommonDTO saveCategory(@RequestBody List<Category> categories) {
        return configService.saveCategory(categories);
    }

    @GetMapping("/category")
    public CommonDTO queryCategory() {
        return configService.queryCategory();
    }


}
