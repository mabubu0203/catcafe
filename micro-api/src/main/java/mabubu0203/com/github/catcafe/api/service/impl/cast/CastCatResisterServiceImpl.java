package mabubu0203.com.github.catcafe.api.service.impl.cast;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import mabubu0203.com.github.catcafe.api.controller.cast.service.CastCatResisterService;
import mabubu0203.com.github.catcafe.api.controller.cast.service.model.input.CastCatResisterServiceInput;
import mabubu0203.com.github.catcafe.api.controller.cast.service.model.output.CastCatResisterServiceOutput;
import mabubu0203.com.github.catcafe.api.service.impl.cast.converter.CastCatResisterServiceConverter;
import mabubu0203.com.github.catcafe.domain.repository.cast.CastRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CastCatResisterServiceImpl implements CastCatResisterService {

  private final CastRepository castRepository;
  private final CastCatResisterServiceConverter converter = new CastCatResisterServiceConverter();

  @Override
  @Transactional
  public Mono<CastCatResisterServiceOutput> action(
      CastCatResisterServiceInput input) {
    var receptionTime = this.getReceptionTime();
    return Optional.of(input)
        .map(this.converter::fromInput)
        .map(entity -> this.castRepository.resister(entity, receptionTime))
        .orElseThrow(RuntimeException::new)
        .map(this.converter::toOutput);
  }

}
