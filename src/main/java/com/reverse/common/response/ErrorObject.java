package com.reverse.common.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ApiResponse的Error部分
 *
 * @author huan liu
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Data
public class ErrorObject {
    /**
     * 错误码
     */
    private Integer code;

    /**
     * 返回给研发之类的错误消息
     */
    private String message;

    /**
     * 返回给用户的请求提示
     */
    private String userMessage;

    public ErrorObject(Integer errorCode, String errorMessage) {
        this.code = errorCode;
        this.message = errorMessage;
    }

    @Override
    public String toString() {
        return "ErrorObject [" +
                "code=" + code +
                ", message='" + message + '\'' +
                ']';
    }
}