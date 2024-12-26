package com.reverse.common.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.SneakyThrows;

/**
 * 使用Jackson处理Json序列化和反序列化
 *
 * @author huan liu
 */

public final class JsonUtil {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    static {
        OBJECT_MAPPER.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        OBJECT_MAPPER.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, Boolean.FALSE);
        OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, Boolean.FALSE);
    }

    /**
     * 对象序列化为字符串
     *
     * @param obj 源对象
     * @param <T> 泛型
     * @return 目标字符串
     */
    public static <T> String toJson(T obj) {
        if (obj == null) {
            throw new RuntimeException("Object to Json Error: Null Object.");
        }

        try {
            return obj instanceof String ? (String) obj : OBJECT_MAPPER.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Object to Json Error.", e);
        }
    }

    /**
     * 字符串解码为对象
     *
     * @param json  被解析json
     * @param clazz 类元
     * @param <T>   泛型 任意非基本类型
     * @return 目标对象
     */
    @SneakyThrows
    @SuppressWarnings("unchecked")
    public static <T> T toObject(String json, Class<T> clazz) {
        if (json == null || json.length() == 0 || clazz == null) {
            throw new RuntimeException("Json to Object Error: Null Object.");
        }

        try {
            return clazz.equals(String.class) ? (T) json : OBJECT_MAPPER.readValue(json, clazz);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Json to Object Error.", e);
        }
    }

    public static <T> T decode(String json, TypeReference<T> reference) {
        try {
            return OBJECT_MAPPER.readValue(json, reference);
        } catch (Exception e) {
            throw new RuntimeException("Json decode error ", e);
        }
    }
}