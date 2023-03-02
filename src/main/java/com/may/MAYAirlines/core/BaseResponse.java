package com.may.MAYAirlines.core;

import com.fasterxml.jackson.annotation.JsonView;

public class BaseResponse {

    private final Response response;


    public BaseResponse(Response response) {
        this.response = response;
        this.message = response.getMessage();
    }

    @JsonView(View.Base.class)
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
