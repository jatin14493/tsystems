package com.notification.exception;

import com.notification.response.ErrorCodes;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValidationException extends RuntimeException {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private ErrorCodes errorCode;

    public ValidationException(String message) {
        super(message);
    }

    /**
     * Instantiates a new Validation Exception.
     *
     * @param message   the message
     * @param throwable the throwable
     */
    public ValidationException(String message, Throwable throwable) {
        super(message, throwable);
    }

    /**
     * Instantiates a new Validation Exception.
     *
     * @param errorCode the error code
     */
    public ValidationException(ErrorCodes errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    @Override
    public String getMessage() {
        return String.format("Validation Exception occurred. message ::" + " %s, error code :: %s",
                super.getMessage(), errorCode);
    }
}
