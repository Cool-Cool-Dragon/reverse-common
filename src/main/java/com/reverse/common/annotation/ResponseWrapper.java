package com.reverse.common.annotation;

import java.lang.annotation.*;

/**
 * 返回报文统一修饰增强
 *
 * @author huan liu
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ResponseWrapper {
}