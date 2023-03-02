package com.may.MAYAirlines.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CountryCodeExistException extends RuntimeException{

    private final ErrorCode errorCode;

    public CountryCodeExistException(ErrorCode errorCode) {
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
