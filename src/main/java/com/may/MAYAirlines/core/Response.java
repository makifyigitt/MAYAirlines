package com.may.MAYAirlines.core;

public enum Response {
    CREATE("Creation is successfully"),
    UPDATE("Data is updated successfully"),
    ACTIVE("Activations is successfully"),
    DELETE("Inactivation is successfully"),
    ADD("Data is added successfully")
    ;

    private final String message;
    Response(String message){
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
