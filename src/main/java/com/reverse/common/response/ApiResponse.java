package com.reverse.common.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.reverse.common.error.BasicError;
import com.reverse.common.error.ErrorI;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Rest接口API的统一Response封装
 *
 * @author huan liu
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> implements Serializable {
    private static final long serialVersionUID = -2759898227625478761L;
    private Integer code;
    private String message;
    private String userMessage;
    private T data;
    private String traceId;

    private ApiResponse(Integer code, String msg, T data) {
        this.code = code;
        this.message = msg;
        this.data = data;
    }

    /**
     * 成功返回，主要用于CUD类操作，无业务操作
     *
     * @return 成功信息
     */
    public static <T> ApiResponse<T> ok() {
        ErrorObject errorObject = new ErrorObject(
                BasicError.OK.getErrorCode(),
                BasicError.OK.getErrorMessage()
        );
        return of(errorObject, null);
    }

    /**
     * 成功返回，主要用于R类操作，有业务操作
     *
     * @param data 业务数据区
     * @return 成功信息
     */
    public static <T> ApiResponse<T> ok(T data) {
        ErrorObject errorObject = new ErrorObject(
                BasicError.OK.getErrorCode(),
                BasicError.OK.getErrorMessage()
        );
        return of(errorObject, data);

    }

    /**
     * 自定义错误信息
     *
     * @param errorMessage 具体错误文字
     * @return 错误信息
     */
    public static <T> ApiResponse<T> fail(String errorMessage) {
        ErrorObject errorObject = new ErrorObject(
                BasicError.ERROR.getErrorCode(),
                errorMessage
        );
        return of(errorObject, null);
    }

    public static <T> ApiResponse<T> fail(ErrorI error, T data) {
        ErrorObject errorObject = new ErrorObject(error.getErrorCode(),
                error.getErrorMessage()
        );
        return of(errorObject, data);
    }

    private static <T> ApiResponse<T> of(ErrorObject error, T data) {
        return new ApiResponse<>(error.getCode(), error.getMessage(), data);
    }
}