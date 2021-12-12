package mabubu0203.com.github.catcafe.api.service.impl.cast;

import lombok.RequiredArgsConstructor;
import mabubu0203.com.github.catcafe.api.controller.cast.service.CastDeleteService;
import mabubu0203.com.github.catcafe.api.controller.cast.service.model.input.CastDeleteServiceInput;
import mabubu0203.com.github.catcafe.api.controller.cast.service.model.output.CastDeleteServiceOutput;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CastDeleteServiceImpl implements CastDeleteService {

  @Override
  @Transactional
  public Mono<CastDeleteServiceOutput> action(CastDeleteServiceInput input) {
    return null;
  }

}
