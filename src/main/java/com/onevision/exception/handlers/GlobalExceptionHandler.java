package com.onevision.exception.handlers;

import com.onevision.dto.ResponseData;
import com.onevision.exception.BookAlreadyExistsException;
import com.onevision.exception.BookNotSavedException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.onevision.dto.ResponseData.response;

@RestControllerAdvice
@Slf4j
@RequiredArgsConstructor
public class GlobalExceptionHandler {


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseData<String>> handleException(Exception ex) {
        log.error("Unknown error. " , ex);
        return response(ex.getMessage() , HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseData<String>> handleValidationException(Exception ex) {
        log.error("Validation failed. " , ex);
        return response( ex.getMessage() , HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BookNotSavedException.class)
    public ResponseEntity<ResponseData<String>> handleBookNotSaved(Exception ex) {
        log.error("handleBookNotSaved() triggered by " , ex);
        return response(ex.getMessage() , HttpStatus.SERVICE_UNAVAILABLE);
    }

    @ExceptionHandler(BookAlreadyExistsException.class)
    public ResponseEntity<ResponseData<String>> handleBookAlreadyExists(Exception ex){
        log.error("handleBookAlreadyExists() triggered by "  , ex);
        return response(ex.getMessage() , HttpStatus.BAD_REQUEST);
    }

}
