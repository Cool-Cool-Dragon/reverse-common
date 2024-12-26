package com.reverse.common.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 错误码说明:错误码为整型类型，正常为"200"，其它错误共5位，分为两个部分：一位错误等级+四位数字编号 错误等级: 1表示错误来源于用户，比如参数错误 2表示错误来源于当前系统，往往是业务逻辑出错，或程序健壮性差等问题 3表示错误来源于第三方服务 四位数字编号从 0001 到 9999，大类之间的步长间距预留 100 一个下游系统可以当成一个大类
 *
 * @author huan liu
 */
@Getter
@AllArgsConstructor
public enum BasicError implements ErrorI {
    /**
     * 响应成功
     */
    OK(200, "Success", "成功"),

    /**
     * 用户输入不合法
     */
    ERROR_PARAM_INVALID(10000, "Param Invalid", "参数不匹配, 请检查输入"),

    /**
     * 规则校验失败
     */
    ERROR_RULE_INVALID(10100, "Rule Invalid", "规则校验失败"),

    /**
     * 自身系统错误
     */
    ERROR(20000, "Fail", "系统异常, 请稍后再试"),

    /**
     * 下游系统异常
     */
    DOWN_SYSTEM_FAILED(30000, "Down system failed", "下游系统异常"),

    /**
     * 下游系统异常
     */
    SALE_MANAGE_FAILED(30100, "Down system  A failed", "下游系统A异常"),
    ;

    private Integer errorCode;
    private String errorMessage;
    private String userErrorMessage;
}