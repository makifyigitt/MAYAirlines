package com.may.MAYAirlines.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AirportNotActiveException extends RuntimeException{
    private final ErrorCode errorCode;

    public AirportNotActiveException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    @Override
    public String getMessage() {
        return errorCode.getMessage();
    }

    public int getErrorCode(){
        return errorCode.getErrorCode();
    }
}
