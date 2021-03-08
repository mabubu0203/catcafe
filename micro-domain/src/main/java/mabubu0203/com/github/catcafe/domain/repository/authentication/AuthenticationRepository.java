package mabubu0203.com.github.catcafe.domain.repository.authentication;

import java.time.LocalDateTime;
import mabubu0203.com.github.catcafe.domain.entity.authentication.XApiKeyEntity;
import mabubu0203.com.github.catcafe.domain.value.XApiKeyToken;
import reactor.core.publisher.Mono;

public interface AuthenticationRepository {

  Mono<XApiKeyEntity> findOne(XApiKeyToken token, LocalDateTime receptionTime);

  Mono<XApiKeyEntity> generate(XApiKeyEntity xApiKey, LocalDateTime receptionTime);

}
