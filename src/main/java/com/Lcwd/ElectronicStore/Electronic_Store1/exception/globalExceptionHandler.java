package com.Lcwd.ElectronicStore.Electronic_Store1.exception;

import com.Lcwd.ElectronicStore.Electronic_Store1.payloads.ApiResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class globalExceptionHandler {
    //handle resource not found exception
    private Logger logger = LoggerFactory.getLogger(globalExceptionHandler.class);

        @ExceptionHandler(ResourceNotFoundException.class)
        public ResponseEntity<ApiResponseMessage> resourceNotFoundException(ResourceNotFoundException ex) {

            ApiResponseMessage response = ApiResponseMessage.builder().message(ex.getMessage()).status(HttpStatus.NOT_FOUND).success(true).build();
            logger.info("Exception handler invoked !!");
            return new ResponseEntity(response, HttpStatus.NOT_FOUND);
        }

        //MethodArgumentNotValidException
        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<Map<String, Object>> methodArgumentNotValidException(MethodArgumentNotValidException ex) {

            List<ObjectError> allErrors = ex.getBindingResult().getAllErrors();
            HashMap<String, Object> response = new HashMap<>();
            allErrors.stream().forEach(objectError -> {
                String message = objectError.getDefaultMessage();
                String field = ((FieldError) objectError).getField();
                response.put(field, message);
            });
            return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        }
    }


