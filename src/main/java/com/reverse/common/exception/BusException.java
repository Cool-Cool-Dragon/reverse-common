package com.reverse.common.exception;

import com.reverse.common.error.ErrorI;

/**
 * 业务异常
 *
 * @author huan liu
 */
public class BusException extends BaseException {

    private static final long serialVersionUID = -6531266984652888315L;

    public BusException(String errMessage) {
        super(errMessage);
    }

    public BusException(String errMessage, Throwable cause) {
        super(errMessage, cause);
    }

    public BusException(Throwable cause) {
        super(cause);
    }

    public BusException(ErrorI errorI) {
        super(errorI);
    }

    public BusException(ErrorI errorI, Throwable cause) {
        super(errorI, cause);
    }
}