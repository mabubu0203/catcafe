package mabubu0203.com.github.catcafe.api.handler;

import java.util.Optional;
import mabubu0203.com.github.catcafe.common.exception.ResourceNotFoundException;
import org.openapitools.model.InlineResponse404;
import org.openapitools.model.ResourceError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import reactor.core.publisher.Mono;

@RestControllerAdvice(annotations = {RestController.class})
public class ResourceNotFoundHandler {

  @ExceptionHandler({ResourceNotFoundException.class})
  public Mono<ResponseEntity<InlineResponse404>> exceptions(ResourceNotFoundException e) {
    return Optional.of(e)
        .map(ResourceNotFoundException::getMessage)
        .map(new ResourceError()::message)
        .map(new InlineResponse404()::resourceError)
        .map(response -> new ResponseEntity<>(response, null, HttpStatus.NOT_FOUND))
        .map(Mono::just)
        .orElseThrow(RuntimeException::new);
  }

}
