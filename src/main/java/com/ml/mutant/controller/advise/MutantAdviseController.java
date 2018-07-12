package com.ml.mutant.controller.advise;

import com.ml.mutant.exception.MutantException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MutantAdviseController {

    @ExceptionHandler(value = MutantException.class)
    public ResponseEntity handle(MutantException e){
        return new ResponseEntity(HttpStatus.FORBIDDEN);
    }

}
