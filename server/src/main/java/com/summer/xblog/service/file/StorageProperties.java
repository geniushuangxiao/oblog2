package com.summer.xblog.service.file;

import org.springframework.context.annotation.Configuration;

import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
public class StorageProperties {
    //用户目录
    private static final String USER_HOME = "user.home";
    private static final String FILE_PATH = "/TechTopic/file/";

    /**
     * 当前操作用户文件路径
     * @param username 操作用户名
     * @return 文件路径
     */
    public String getLocation(String username) {
        return System.getProperties().getProperty(USER_HOME) + FILE_PATH + username;
    }

    /**
     * 获取当前操作用户的文件路径
     * @param username 操作用户名
     * @return 操作用户文件路径
     */
    public Path getPath(String username) {
        return Paths.get(this.getLocation(username));
    }

}
