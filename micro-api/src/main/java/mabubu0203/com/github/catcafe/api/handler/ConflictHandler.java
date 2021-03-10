package mabubu0203.com.github.catcafe.api.handler;

import java.util.Optional;
import org.openapitools.model.ExclusiveError;
import org.openapitools.model.InlineResponse409;
import org.springframework.core.NestedRuntimeException;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import reactor.core.publisher.Mono;

@RestControllerAdvice(annotations = {RestController.class})
public class ConflictHandler {

  @ExceptionHandler({OptimisticLockingFailureException.class})
  public Mono<ResponseEntity<InlineResponse409>> exceptions(OptimisticLockingFailureException e) {
    return Optional.of(e)
        .map(NestedRuntimeException::getMessage)
        .map(new ExclusiveError()::message)
        .map(new InlineResponse409()::exclusiveError)
        .map(response -> new ResponseEntity<>(response, null, HttpStatus.CONFLICT))
        .map(Mono::just)
        .orElseThrow(RuntimeException::new);
  }

}
