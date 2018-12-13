package com.summer.xblog.service;

import com.summer.xblog.dao.UserAuthorityRepository;
import com.summer.xblog.dao.UserRepository;
import com.summer.xblog.entity.User;
import com.summer.xblog.entity.UserAuthority;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.Iterator;

@Service
public class DefaultUserInit implements InitializingBean {
    private static final Logger log = LoggerFactory.getLogger(DefaultUserInit.class);
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserAuthorityRepository userAuthorityRepository;
    @Autowired
    private Environment env;
    public void afterPropertiesSet() {
        //向数据库中添加ADMIN和USER权限
        if(!exists("ADMIN")) {
            UserAuthority admin = new UserAuthority("ADMIN");
            userAuthorityRepository.save(admin);
            log.info("Add default UserAuthority ADMIN");
        }
        if(!exists("USER")) {
            UserAuthority user = new UserAuthority("USER");
            userAuthorityRepository.save(user);
            log.info("Add default UserAuthority USER");
        }
        //没有用户，添加默认用户
        Iterator<User> iterator = userRepository.findAll().iterator();
        if(!iterator.hasNext()) {
            userService.privateAddUser(env.getProperty("default.admin.username"), env.getProperty("default.admin.password"), "ADMIN", env.getProperty("default.admin.email"));
        }
    }

    private boolean exists(String role) {
        return userAuthorityRepository.findByRole(role).size() > 0;
    }

}
