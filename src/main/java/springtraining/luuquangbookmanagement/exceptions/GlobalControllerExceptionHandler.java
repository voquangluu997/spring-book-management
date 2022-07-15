package springtraining.luuquangbookmanagement.exceptions;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Collections;
import java.util.List;

@Getter
@AllArgsConstructor(access = AccessLevel.PUBLIC)
class ErrorModel {
    private List<String> message;
    private String path;
}

@RestControllerAdvice
public class GlobalControllerExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<ErrorModel> handleBookNotFoundException(BookNotFoundException ex, ServletWebRequest request) {
        return new ResponseEntity<>(new ErrorModel(Collections.singletonList(ex.getMessage()), request.getRequest().getRequestURI()), HttpStatus.NOT_FOUND);
    }

}
