package handyhire.labourservice.exceptions;

import handyhire.labourservice.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.*;

@ControllerAdvice
public class GlobalExceptionHandler {



    @ExceptionHandler(NullPointerException.class)
    public final ResponseEntity<Object> handleNullPointerException(NullPointerException ex, WebRequest request) {
        ex.printStackTrace();
        return new ResponseEntity<>(new Response(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Value cannot be null"), HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleGenericException(Exception ex, WebRequest request) {
        ex.printStackTrace();
        String exceptionMessage = ex.getMessage();
        exceptionMessage = exceptionMessage.replaceAll("com.example.umadminbackend.exception.DuplicateRecordException: ","");
        return new ResponseEntity<>(new Response(HttpStatus.INTERNAL_SERVER_ERROR.value(), exceptionMessage), HttpStatus.INTERNAL_SERVER_ERROR);
    }


    //Check validations if you add validation rules on DTO class
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Response> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        List<String> errorMessages = ex.getBindingResult().getFieldErrors()
                .stream().map(FieldError::getDefaultMessage).toList();
        return new ResponseEntity<>(new Response(HttpStatus.BAD_REQUEST.value(),"please fill the mandatory feilds",errorMessages), HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(RecordNotFoundException.class)
    public final ResponseEntity<Object> handleRecordNotFoundException(Exception ex, WebRequest request) {
        ex.printStackTrace();

        return new ResponseEntity<>(new Response(HttpStatus.NOT_FOUND.value(), ex.getMessage()), HttpStatus.NOT_FOUND);
    }



    @ExceptionHandler(JpaSystemException.class)
    public final ResponseEntity<Object> handleTimeoutException(Exception ex, WebRequest request) {
        ex.printStackTrace();
        String exceptionMessage = ex.getMessage();
        if(Objects.equals(exceptionMessage,"transaction timeout expired")){
            return new ResponseEntity<>(new Response(HttpStatus.REQUEST_TIMEOUT.value(), ex.getMessage()), HttpStatus.REQUEST_TIMEOUT);
        }else {
            return new ResponseEntity<>(new Response(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ExceptionHandler(DuplicateEntryException.class)
    public final ResponseEntity<Object> handleDuplicateEntryException(DuplicateEntryException ex, WebRequest request) {
        ex.printStackTrace();
        return new ResponseEntity<>(new Response(HttpStatus.CONFLICT.value(), ex.getMessage()), HttpStatus.CONFLICT);
    }
}

