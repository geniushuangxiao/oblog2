package com.summer.xblog.service;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.summer.xblog.dao.UserRepository;
import com.summer.xblog.entity.User;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class ActivateLinkCache {
    //缓存池大小
    private static final int CACHE_SIZE = 5000;
    private static final int EXPIRE_TIME_MINUTES = 30;
    private Cache<String, ActivateLink> cache = CacheBuilder.newBuilder()
            .maximumSize(CACHE_SIZE)
            .expireAfterWrite(EXPIRE_TIME_MINUTES, TimeUnit.MINUTES) // 设置缓存30分钟后失效
            .concurrencyLevel(Runtime.getRuntime().availableProcessors()) //设置并发级别为cpu核心数
            .recordStats() // 开启缓存统计
            .build();

    @Autowired
    private UserRepository userRepository;

    public void push(User user, String random) {
        cache.put(random, ActivateLink.newInstance(user, random));
    }

    public ActivateLink get(String random) {
        return cache.getIfPresent(random);
    }

    /**判断激活随机字符串是否已经存在*/
    boolean exists(String random) {
        return cache.getIfPresent(random) != null;
    }

    void remove(final ActivateLink link) {
        cache.invalidate(link.getRandom());
    }

    /**
     * 缓存是否存满
     * @return true表示存满
     */
    boolean full() {
        return cache.size() >= CACHE_SIZE;
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
