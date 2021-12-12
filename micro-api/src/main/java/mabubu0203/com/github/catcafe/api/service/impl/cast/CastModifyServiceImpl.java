package mabubu0203.com.github.catcafe.api.service.impl.cast;

import lombok.RequiredArgsConstructor;
import mabubu0203.com.github.catcafe.api.controller.cast.service.CastModifyService;
import mabubu0203.com.github.catcafe.api.controller.cast.service.model.input.CastModifyServiceInput;
import mabubu0203.com.github.catcafe.api.controller.cast.service.model.output.CastModifyServiceOutput;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CastModifyServiceImpl implements CastModifyService {

  @Override
  @Transactional
  public Mono<CastModifyServiceOutput> action(CastModifyServiceInput input) {
    return null;
  }

}
