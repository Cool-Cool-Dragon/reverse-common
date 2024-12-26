package com.reverse.common.exception;

import com.reverse.common.error.BasicError;
import com.reverse.common.error.ErrorI;
import lombok.Getter;
import lombok.Setter;

/**
 * BaseException
 *
 * @author huan liu
 */
@Getter
@Setter
public abstract class BaseException extends RuntimeException{
    /**
     * 响应错误码
     */
    protected Integer errno;

    /**
     * 错误描述
     */
    protected String error;

    /**
     * Error 枚举映射错误数据
     */
    protected ErrorI errorI;

    /**
     * 原始异常 用于异常堆栈溯源
     */
    protected Throwable exception;


    /**
     * 自定义错误信息
     *
     * @param errMessage 自定义错误信息
     */
    public BaseException(String errMessage) {
        super(errMessage);
        this.errorI = BasicError.ERROR;
        this.errno = this.errorI.getErrorCode();
        this.error = this.errorI.getUserErrorMessage();
    }

    /**
     * 用于包装原始异常, 二次抛出
     *
     * @param errMessage 自定义错误信息
     * @param cause      异常源
     */
    public BaseException(String errMessage, Throwable cause) {
        super(errMessage, cause);
        this.errorI = BasicError.ERROR;
        this.errno = this.errorI.getErrorCode();
        this.error = this.errorI.getUserErrorMessage();
        this.exception = cause;
    }

    /**
     * 用于包装原始异常, 二次抛出
     *
     * @param cause 异常来源
     */
    public BaseException(Throwable cause) {
        super(cause);
        this.errorI = BasicError.ERROR;
        this.errno = this.errorI.getErrorCode();
        this.error = this.errorI.getUserErrorMessage();
        this.exception = cause;
    }

    /**
     * 枚举定义的异常
     *
     * @param errorI 枚举异常
     */
    public BaseException(ErrorI errorI) {
        super(errorI.getUserErrorMessage());
        this.errorI = errorI;
        this.errno = errorI.getErrorCode();
        this.error = errorI.getUserErrorMessage();
    }

    /**
     * 枚举错误和原始异常
     *
     * @param errorI 错误枚举
     * @param cause  异常来源
     */
    public BaseException(ErrorI errorI, Throwable cause) {
        super(cause);
        this.errorI = errorI;
        this.errno = errorI.getErrorCode();
        this.error = errorI.getUserErrorMessage();
    }


    @Override
    public String toString() {
        return "BaseException [errno=" + errno + ",error" + error + "]";
    }
}