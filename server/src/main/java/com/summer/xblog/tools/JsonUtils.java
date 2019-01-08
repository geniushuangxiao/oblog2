package com.summer.xblog.tools;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Optional;

@Slf4j
public class JsonUtils {
    private static final ObjectMapper mapper;
    static {
        mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL); //属性为NULL不序列化
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);//反序列化时，忽略json中存在，java中不存在的字段
    }
    /**
     * 对象序列化为json
     * @param object 待序列化的对象
     * @return json字符串
     */
    public static Optional<String> toJson(Object object) {
        try {
            return Optional.of(mapper.writeValueAsString(object));
        } catch (JsonProcessingException e) {
            log.error("对象序列化为json失败", e);
            return Optional.empty();
        }
    }

    /**
     * json字符串反序列化为对象
     * @param json 对象的json字符串
     * @param clazz 对象类型
     * @param <T> 泛型
     * @return 对象
     */
    public static <T> Optional<T> toObject(String json, Class<T> clazz) {
        try {
            T t = mapper.readValue(json, clazz);
            return Optional.of(t);
        } catch (IOException e) {
            log.error("反序列化json失败", e);
            return Optional.empty();
        }
    }
}
