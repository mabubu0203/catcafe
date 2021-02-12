package mabubu0203.com.github.catcafe.api.handler;

import java.util.stream.Collectors;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.openapitools.model.ValidationResult;
import org.springframework.beans.TypeMismatchException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import reactor.core.publisher.Mono;

@RestControllerAdvice(annotations = {RestController.class})
public class ValidationHandler {

  @ExceptionHandler({TypeMismatchException.class})
  public Mono<ResponseEntity<ValidationResult>> exceptions(TypeMismatchException e) {
    var result = new ValidationResult();
    result.setKey(e.getLocalizedMessage());
    return Mono.just(new ResponseEntity<>(result, null, HttpStatus.BAD_REQUEST));
  }

  @ExceptionHandler({BindException.class})
  public Mono<ResponseEntity<ValidationResult>> exceptions(BindException e) {
    var list = e.getBindingResult().getAllErrors()
        .stream()
        .map(DefaultMessageSourceResolvable::getDefaultMessage)
        .collect(Collectors.toList());
    var result = new ValidationResult();
    result.setMessages(list);

    return Mono.just(new ResponseEntity<>(result, null, HttpStatus.BAD_REQUEST));
  }

  @ExceptionHandler({ConstraintViolationException.class})
  public Mono<ResponseEntity<ValidationResult>> exceptions(ConstraintViolationException e) {
    var list = e.getConstraintViolations().stream()
        .map(this::get)
        .collect(Collectors.toList());
    var result = new ValidationResult();

    result.setMessages(list);
    return Mono.just(new ResponseEntity<>(result, null, HttpStatus.BAD_REQUEST));
  }

  private String get(ConstraintViolation<?> violation) {
    var a = violation.getPropertyPath().toString();
//    var b = violation.getInvalidValue().toString();
    var b = "は";
    var c = violation.getMessage();
    return a + b + c;
  }

  @ExceptionHandler({MethodArgumentNotValidException.class})
  public Mono<ResponseEntity<ValidationResult>> exceptions(MethodArgumentNotValidException e) {
    var list = e.getBindingResult().getAllErrors()
        .stream()
        .map(DefaultMessageSourceResolvable::getDefaultMessage)
        .collect(Collectors.toList());
    var result = new ValidationResult();
    result.setMessages(list);
    return Mono.just(new ResponseEntity<>(result, null, HttpStatus.BAD_REQUEST));
  }

}
