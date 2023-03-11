package com.may.MAYAirlines.core.exception;

public enum ErrorCode {
    CUSTOMER_NOT_FOUND(1,"Müşteri bulunamadı."),
    USERNAME_IS_USED(2,"Kulanıcı adı başkası tarafından kullanılmaktadır."),
    CITY_NOT_FOUND(3,"Şehir bulunamadı"),
    COUNTRY_NOT_FOUND(4,"Ülke bulunamadı"),
    FLIGHT_NOT_FOUND(5,"Uçuş bulunamadı"),
    AIRPLANE_NOT_FOUND(6,"Uçak bulunamadı"),
    AIRPORT_NOT_FOUND(7,"Havalimanı bulunamadı."),
    AIRPORT_NOT_ACTIVE(8,"Havalimanı aktif değil."),
    AIRPORT_CODE_IS_EXIST(9,"Aynı kısaltmaya sahip bir havaalanı bulunmaktadır."),
    COUNTRY_CODE_IS_EXIST(10,"Aynı kısaltmaya sahip bir ülke bulunmaktadır."),
    RESERVATION_NOT_FOUND(11,"Rezervasyon bulunumadı."),
    USER_NOT_FOUND(12,"Kullanıcı bulunamadı."),
    PASSWORD_IS_WRONG(13,"Girdiğiniz parola hatalıdır.")
    ;

    private final int errorCode;
    private final String message;

    ErrorCode(int errorCode, String message){
        this.errorCode=errorCode;
        this.message=message;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getMessage() {
        return message;
    }
}
