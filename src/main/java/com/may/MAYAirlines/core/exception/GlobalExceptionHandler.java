package com.may.MAYAirlines.core.exception;

import jakarta.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @NotNull
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  @NotNull HttpHeaders headers,
                                                                  @NotNull HttpStatusCode status,
                                                                  @NotNull WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error ->{
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AirplaneNotFoundException.class)
    public ResponseEntity airplaneNotFoundExceptionHandler(AirplaneNotFoundException exception){
        logger.error("Custom error handling. Error code: "+exception.getErrorCode(),
                new AirplaneNotFoundException(ErrorCode.AIRPLANE_NOT_FOUND));
        return new ResponseEntity(exception.getMessage(),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AirportCodeExistException.class)
    public ResponseEntity airportCodeExistExceptionHandler(AirportCodeExistException exception){
        logger.error("Custom error handling. Error code: "+exception.getErrorCode(),
                new AirportCodeExistException(ErrorCode.AIRPORT_CODE_IS_EXIST));
        return new ResponseEntity(exception.getMessage(),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AirportNotActiveException.class)
    public ResponseEntity airportNotActiveExceptionHandler(AirportNotActiveException exception){
        logger.error("Custom error handling. Error code: "+exception.getErrorCode(),
                new AirportNotActiveException(ErrorCode.AIRPORT_NOT_ACTIVE));
        return new ResponseEntity(exception.getMessage(),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AirportNotFoundException.class)
    public ResponseEntity airportNotFoundExceptionHandler(AirplaneNotFoundException exception){
        logger.error("Custom error handling. Error code: "+exception.getErrorCode(),
                new AirportNotFoundException(ErrorCode.AIRPORT_NOT_FOUND));
        return new ResponseEntity(exception.getMessage(),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CityNotFoundException.class)
    public ResponseEntity cityNotFoundExceptionHandler(CityNotFoundException exception){
        logger.error("Custom error handling. Error code: "+exception.getErrorCode(),
                new CityNotFoundException(ErrorCode.CITY_NOT_FOUND));
        return new ResponseEntity(exception.getMessage(),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CountryCodeExistException.class)
    public ResponseEntity countryCodeExistExceptionHandler(CountryCodeExistException exception){
        logger.error("Custom error handling. Error code: "+exception.getErrorCode(),
                new CountryCodeExistException(ErrorCode.COUNTRY_CODE_IS_EXIST));
        return new ResponseEntity(exception.getMessage(),HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(CountryNotFoundException.class)
    public ResponseEntity countryNotFoundExceptionHandler(CountryNotFoundException exception){
        logger.error("Custom error handling. Error code: "+exception.getErrorCode(),
                new CountryNotFoundException(ErrorCode.COUNTRY_NOT_FOUND));
        return new ResponseEntity(exception.getMessage(),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<?> customerNotFoundExceptionHandler(CustomerNotFoundException exception){
        logger.error("Custom error handling. Error code: "+exception.getErrorCode(),
                new CustomerNotFoundException(ErrorCode.CUSTOMER_NOT_FOUND));
        return new ResponseEntity<>(exception.getMessage(),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(FlightNotFoundException.class)
    public ResponseEntity flightNotFoundExceptionHandler(FlightNotFoundException exception){
        logger.error("Custom error handling. Error code: "+exception.getErrorCode(),
                new FlightNotFoundException(ErrorCode.FLIGHT_NOT_FOUND));
        return new ResponseEntity(exception.getMessage(),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ReservationNotFound.class)
    public ResponseEntity<?> reservationNotFoundHandler(ReservationNotFound exception){
        logger.error("Custom error handling. Error code: "+exception.getErrorCode(),
                new ReservationNotFound(ErrorCode.RESERVATION_NOT_FOUND));
        return new ResponseEntity<>(exception.getMessage(),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UsernameExistException.class)
    public ResponseEntity usernameExistExceptionHandler(UsernameExistException exception){
        logger.error("Custom error handling. Error code: "+exception.getErrorCode(),
                new UsernameExistException(ErrorCode.USERNAME_IS_USED));
        return new ResponseEntity(exception.getMessage(),HttpStatus.BAD_REQUEST);
    }

//    @ExceptionHandler(UsernameNotFoundException.class)
//    public ResponseEntity usernameNotFoundExceptionHandler(UsernameNotFoundException exception){
//        logger.error(exception.getMessage(),exception.getCause());
//        return new ResponseEntity(exception.getMessage(),HttpStatus.NOT_FOUND);
//    }
//TODO durma göre usernotfound için hata yakalamayı düşünebilirsin
}
