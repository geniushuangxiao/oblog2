package com.summer.xblog.service;

import com.summer.xblog.dao.UserRepository;
import com.summer.xblog.entity.User;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ActivateLinkCache {
    //缓存池大小
    private static final int CACHE_SIZE = 5000;
    private static final int EXPIRE_TIME = 30 * 60 * 1000;
    private Map<String, ActivateLink> links = new HashMap<>();

    @Autowired
    private UserRepository userRepository;

    public void push(User user, String random) {
        links.put(random, ActivateLink.newInstance(user, random));
    }

    public ActivateLink get(String random) {
        return links.get(random);
    }

    /**判断激活随机字符串是否已经存在*/
    boolean exists(String random) {
        return links.get(random) != null;
    }

    void remove(final ActivateLink link) {
        links.remove(link.getRandom());
    }

    /**
     * 缓存是否存满
     * @return true表示存满
     */
    boolean full() {
        return links.size() >= CACHE_SIZE;
    }

    /**
     * 清除半小时未激活的注册
     */
    public void clean() {
        long current = System.currentTimeMillis();
        links.keySet().forEach(key -> {
            final ActivateLink value = this.links.get(key);
            boolean expired = (current - value.getRegisterTime()) > EXPIRE_TIME;
            if(expired) {
                //从数据库删除
                this.userRepository.deleteById(value.getUserId());
                //从缓存删除
                this.remove(value);
            }
        });
    }
}
@Data
class ActivateLink {
    private long userId;
    private String username;
    private String random;
    private long registerTime;
    static ActivateLink newInstance(User user, String random) {
        ActivateLink link = new ActivateLink();
        link.setUserId(user.getId());
        link.setUsername(user.getUsername());
        link.setRandom(random);
        link.setRegisterTime(System.currentTimeMillis());
        return link;
    }
}
