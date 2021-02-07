package mabubu0203.com.github.catcafe.api.handler;

import mabubu0203.com.github.catcafe.api.components.security.HeaderNotFoundException;
import mabubu0203.com.github.catcafe.api.components.security.TokenNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import reactor.core.publisher.Mono;

// 認証エラーはRestControllerAdviceの範疇ではない
@RestControllerAdvice(annotations = {RestController.class})
public class UnauthorizedHandler {

  @ExceptionHandler({HeaderNotFoundException.class, TokenNotFoundException.class})
  public Mono<ResponseEntity<Object>> exceptions(Throwable e) {
    return Mono.just(new ResponseEntity<>(null, null, HttpStatus.UNAUTHORIZED));
  }

}
