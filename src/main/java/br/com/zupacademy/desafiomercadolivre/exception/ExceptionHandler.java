package br.com.zupacademy.desafiomercadolivre.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<FieldErrorRequest>> handle(MethodArgumentNotValidException exception){
        List<FieldErrorRequest> erros = new ArrayList<>();
        exception.getFieldErrors().forEach(e ->{
            erros.add(new FieldErrorRequest(e.getField(), e.getDefaultMessage()));
        });

        return ResponseEntity.badRequest().body(erros);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<?> handle(ResponseStatusException exception){
        return ResponseEntity.badRequest().body(exception.getReason());
    }
}
