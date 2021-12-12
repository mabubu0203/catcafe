package mabubu0203.com.github.catcafe.api.service.impl.cast;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import mabubu0203.com.github.catcafe.api.controller.cast.service.CastCatDeleteService;
import mabubu0203.com.github.catcafe.api.controller.cast.service.model.input.CastCatDeleteServiceInput;
import mabubu0203.com.github.catcafe.api.controller.cast.service.model.output.CastCatDeleteServiceOutput;
import mabubu0203.com.github.catcafe.api.service.impl.cast.converter.CastCatDeleteServiceConverter;
import mabubu0203.com.github.catcafe.domain.repository.cast.CastRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CastCatDeleteServiceImpl implements CastCatDeleteService {

  private final CastRepository castRepository;
  private final CastCatDeleteServiceConverter converter = new CastCatDeleteServiceConverter();

  @Override
  @Transactional
  public Mono<CastCatDeleteServiceOutput> action(CastCatDeleteServiceInput input) {
    var receptionTime = this.getReceptionTime();
    return Optional.of(input)
        .map(this.converter::fromInput)
        .map(entity -> this.castRepository.logicalDelete(entity, receptionTime))
        .orElseThrow(RuntimeException::new)
        .map(this.converter::toOutput);
  }

}
