package com.summer.xblog.timer;

import com.summer.xblog.service.ActivateLinkCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class CleanActivateLinkTimer {
    private static final Logger log = LoggerFactory.getLogger(CleanActivateLinkTimer.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Autowired
    private ActivateLinkCache cache;

    @Scheduled(cron = "0 0/10 * * * *")    //每10分钟执行1次
    public void reportCurrentTimeUsingCronExpression() {
        log.info("CleanActivateLinkTimer run at Time:{}", dateFormat.format(new Date()));
        cache.clean();
    }
}
