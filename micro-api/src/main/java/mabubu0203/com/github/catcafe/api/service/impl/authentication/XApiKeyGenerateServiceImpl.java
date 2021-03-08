package mabubu0203.com.github.catcafe.api.service.impl.authentication;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import mabubu0203.com.github.catcafe.api.controller.authentication.service.XApiKeyGenerateService;
import mabubu0203.com.github.catcafe.api.controller.authentication.service.model.input.XApiKeyGenerateServiceInput;
import mabubu0203.com.github.catcafe.api.controller.authentication.service.model.output.XApiKeyGenerateServiceOutput;
import mabubu0203.com.github.catcafe.api.service.impl.authentication.converter.XApiKeyGenerateServiceConverter;
import mabubu0203.com.github.catcafe.domain.entity.authentication.XApiKeyEntity;
import mabubu0203.com.github.catcafe.domain.repository.authentication.AuthenticationRepository;
import mabubu0203.com.github.catcafe.domain.value.XApiKeyToken;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class XApiKeyGenerateServiceImpl implements XApiKeyGenerateService {

  private final AuthenticationRepository authenticationRepository;
  private final XApiKeyGenerateServiceConverter converter = new XApiKeyGenerateServiceConverter();

  @Override
  public Mono<XApiKeyGenerateServiceOutput> action(
      XApiKeyGenerateServiceInput input) {
    var receptionTime = this.getReceptionTime();
    var token = this.getToken(input.getRandom(), receptionTime);
    return
        Optional.of(input)
            .map(this.converter::fromInput)
            .map(builder ->
                builder
                    .token(token)
                    .startDateTime(receptionTime)
                    .endDateTime(receptionTime.plusDays(1))
            )
            .map(XApiKeyEntity.XApiKeyEntityBuilder::build)
            .map(entity -> this.authenticationRepository.generate(entity, receptionTime))
            .orElseThrow(RuntimeException::new)
            .map(this.converter::toOutput);
  }

  private XApiKeyToken getToken(String random, LocalDateTime receptionTime) {
    var receptionTimeStr = receptionTime.toString();
    var letter = Optional.ofNullable(random)
        .map(receptionTimeStr::concat)
        .map(str -> str.getBytes(StandardCharsets.UTF_8))
        .orElse(receptionTimeStr.getBytes(StandardCharsets.UTF_8));
    var encoded = Base64.getEncoder().encodeToString(letter);
    return new XApiKeyToken(encoded);
  }

}
