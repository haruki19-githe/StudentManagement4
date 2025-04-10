package raisetech.StudentManagement.controller.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import raisetech.StudentManagement.exception.ResourceNotFoundException;

@RestControllerAdvice
public class Exception {
    //例外が発生したらここに飛んでくる
    @ExceptionHandler(ResourceNotFoundException.class)
    //キャッチして、例外内容が入って（exに）ハンドリングされた後に
    public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException ex) {
        //BAD_REQUEST（400）を設定してメッセージの内容をbodyに含める
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

}
