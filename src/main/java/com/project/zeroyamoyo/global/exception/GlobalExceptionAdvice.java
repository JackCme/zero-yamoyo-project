package com.project.zeroyamoyo.global.exception;

import com.project.zeroyamoyo.global.response.ResponseWrapper;
import com.project.zeroyamoyo.global.response.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionAdvice {
    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<ResponseWrapper<Object>> handleException(Exception e) {
        if (e instanceof GlobalException) {
            ResultType resultType = ((GlobalException) e).getResultType();
            return new ResponseEntity<>(
                    ResponseWrapper.builder()
                            .result(Result.from(resultType))
                            .build(),
                    resultType.getHttpStatus()
            );
        }

        log.error(e.getMessage(), e);
        return new ResponseEntity<>(
            ResponseWrapper.builder()
                    .result(Result.from(ResultType.INTERNAL_SERVER_ERROR))
                    .build(),
                ResultType.INTERNAL_SERVER_ERROR.getHttpStatus()
        );
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<ResponseWrapper<HashMap<String, String>>> handleMethodArgumentNotValidException(BindException e) {
        List<ObjectError> allErrors = e.getBindingResult().getAllErrors();
        HashMap<String, String> map = new HashMap<>();
        for (ObjectError error : allErrors) {
            FieldError fieldError = (FieldError) error;
            map.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        return new ResponseEntity<>(
                ResponseWrapper.<HashMap<String, String>>builder()
                        .result(Result.from(ResultType.INVALID_REQUEST_PARAMETER))
                        .content(map)
                        .build(),
                ResultType.INVALID_REQUEST_PARAMETER.getHttpStatus()
        );
    }
}
