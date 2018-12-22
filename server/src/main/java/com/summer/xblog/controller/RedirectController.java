package com.summer.xblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 将根目录重定向到index.html，避免前端刷新页面报404
 */
@Controller
public class RedirectController {
    @RequestMapping("/")
    public String redirect() {
        return "redirect:index.html";
    }
}
