package raisetech.StudentManagement.controller.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import raisetech.StudentManagement.exception.ResourceNotFoundException;

import java.util.HashMap;
import java.util.Map;


@RestControllerAdvice
public class GlobalExceptionHandler {
    //例外が発生したらここに飛んでくる
    @ExceptionHandler(ResourceNotFoundException.class)
    //キャッチして、例外内容が入って（exに）ハンドリングされた後に
    public ResponseEntity<Map<String, String>> handleResourceNotFoundException(ResourceNotFoundException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("message", ex.getMessage());
        //BAD_REQUEST（400）を設定してメッセージの内容をbodyに含める
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}
