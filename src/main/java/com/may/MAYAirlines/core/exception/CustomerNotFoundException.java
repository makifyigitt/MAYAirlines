package com.may.MAYAirlines.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CustomerNotFoundException extends RuntimeException{

    private final ErrorCode errorCode;

    public CustomerNotFoundException(ErrorCode errorCode){
        this.errorCode=errorCode;
    }
    @Override
    public String getMessage() {
        return errorCode.getMessage();
    }

    public int getErrorCode(){
        return errorCode.getErrorCode();
    }
}
