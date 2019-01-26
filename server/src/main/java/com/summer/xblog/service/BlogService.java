package com.summer.xblog.service;

import com.summer.xblog.dao.BlogRepository;
import com.summer.xblog.dto.CommonDTO;
import com.summer.xblog.entity.Blog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Optional;

@Service
public class BlogService {
    private static final Logger log = LoggerFactory.getLogger(BlogService.class);
    @Autowired
    private BlogRepository repository;

    @Secured({"ROLE_ADMIN"})
    public CommonDTO save(Principal principal, Blog blog) {
        //博客不属于提交人
        if(!principal.getName().equals(blog.getAuthor())) {
            log.error(String.format("%s modify blog %s, But blog's author is %s", principal.getName(), blog.getName(), blog.getAuthor()));
            return CommonDTO.fail("您没有修改此博客权限", blog);
        }
        long current = System.currentTimeMillis();
        if(0 == blog.getId()) {
            blog.setFirstWriteTime(current);
            blog.setCommitNum(0);
            blog.setStarNum(0);
            blog.setViewNum(0);
        } else {    //阻止伪造博客作者，修改博客行为
            Optional<Blog> oldBlogOptional = repository.findById(blog.getId());
            if(oldBlogOptional.isPresent()) {
                Blog oldBlog = oldBlogOptional.get();
                if(!oldBlog.getAuthor().equals(blog.getAuthor())) {
                    log.error(String.format("Attach happened. %s modify blog %s, But blog's author is %s", principal.getName(), blog.getName(), blog.getAuthor()));
                    return CommonDTO.fail("您没有修改此博客权限", blog);
                }
                blog.setCommitNum(oldBlog.getCommitNum());
                blog.setStarNum(oldBlog.getStarNum());
                blog.setViewNum(oldBlog.getViewNum());
            }
        }
        blog.setLastModifyTime(current);
        Blog save = repository.save(blog);
        return CommonDTO.success(save);
    }

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public boolean deleteById(Principal principal, long id) {
        Optional<Blog> oldBlogOptional = repository.findById(id);
        if(oldBlogOptional.isPresent()) {
            Blog blog = oldBlogOptional.get();
            if(blog.getAuthor().equals(principal.getName())) {
                repository.deleteById(id);
                return true;
            }
        }
        return false;
    }

    public void viewTimeRefresh(long id) {
        Optional<Blog> optional = repository.findById(id);
        if(optional.isPresent()) {
            Blog blog = optional.get();
            blog.setViewNum(blog.getViewNum() + 1);
            repository.save(blog);
        }
    }
}
