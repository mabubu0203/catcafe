package mabubu0203.com.github.catcafe.api.handler;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.openapitools.model.InlineResponse400;
import org.openapitools.model.ValidationError;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;
import reactor.core.publisher.Mono;

@RestControllerAdvice(annotations = {RestController.class})
public class ValidationHandler {

  @ExceptionHandler({BindException.class})
  public Mono<ResponseEntity<InlineResponse400>> exceptions(BindException e) {
    var validationErrors = e.getBindingResult()
        .getFieldErrors()
        .stream()
        .map(this::getValidationError)
        .collect(Collectors.toList());
    return this.setValidationErrors(validationErrors);
  }

  @ExceptionHandler({ConstraintViolationException.class})
  public Mono<ResponseEntity<InlineResponse400>> exceptions(ConstraintViolationException e) {
    var validationErrors = e.getConstraintViolations()
        .stream()
        .map(this::getValidationError)
        .collect(Collectors.toList());
    return this.setValidationErrors(validationErrors);
  }

  @ExceptionHandler({MethodArgumentNotValidException.class})
  public Mono<ResponseEntity<InlineResponse400>> exceptions(
      MethodArgumentNotValidException e) {
    var validationErrors = e.getBindingResult()
        .getFieldErrors()
        .stream()
        .map(this::getValidationError)
        .collect(Collectors.toList());
    return this.setValidationErrors(validationErrors);
  }

  @ExceptionHandler({TypeMismatchException.class})
  public Mono<ResponseEntity<InlineResponse400>> exceptions(TypeMismatchException e) {
    // NumberFormatException をうまく拾えていない
    var validationError = new ValidationError()
        .property(e.getPropertyName())
        .addMessagesItem(e.getMessage());
    var validationErrors = new ArrayList<ValidationError>();
    validationErrors.add(validationError);
    return this.setValidationErrors(validationErrors);
  }

  @ExceptionHandler({WebExchangeBindException.class})
  public Mono<ResponseEntity<InlineResponse400>> exceptions(WebExchangeBindException e) {
    var validationErrors = e.getBindingResult()
        .getFieldErrors()
        .stream()
        .map(this::getValidationError)
        .collect(Collectors.toList());
    return this.setValidationErrors(validationErrors);
  }

  private ValidationError getValidationError(FieldError fieldError) {
    return
        new ValidationError()
            .property(fieldError.getField())
            .addMessagesItem(fieldError.getDefaultMessage());
  }

  private ValidationError getValidationError(ConstraintViolation<?> violation) {
    var property = violation.getPropertyPath().toString();
    var message = property + "は" + violation.getMessage();
    return
        new ValidationError()
            .property(property)
            .addMessagesItem(message);
  }

  private Mono<ResponseEntity<InlineResponse400>> setValidationErrors(
      List<ValidationError> validationErrors) {
    return
        Optional.of(validationErrors)
            .map(new InlineResponse400()::validationErrors)
            .map(response -> new ResponseEntity<>(response, null, HttpStatus.BAD_REQUEST))
            .map(Mono::just)
            .orElseThrow(RuntimeException::new);
  }

}
