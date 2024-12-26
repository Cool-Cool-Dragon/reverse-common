package com.reverse.common.error;

/**
 * 错误信息抽象
 *
 * @author huan liu
 */
public interface ErrorI {
    /**
     * 错误码
     * @return 获取错误码
     */
    Integer getErrorCode();

    /**
     * 错误文本
     * @return 获取用户友好的错误信息
     */
    String getUserErrorMessage();

    /**
     * 错误文本
     * @return 获取技术人员的错误信息
     */
    String getErrorMessage();
}