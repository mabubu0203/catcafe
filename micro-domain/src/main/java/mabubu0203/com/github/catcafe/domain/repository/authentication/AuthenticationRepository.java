package mabubu0203.com.github.catcafe.domain.repository.authentication;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import mabubu0203.com.github.catcafe.domain.entity.authentication.XApiKeyEntity;
import mabubu0203.com.github.catcafe.domain.value.XApiKeyToken;

public interface AuthenticationRepository {

  CompletableFuture<Optional<XApiKeyEntity>> findOne(XApiKeyToken token, LocalDateTime receptionTime);

  CompletableFuture<XApiKeyEntity> generate(XApiKeyEntity xApiKey, LocalDateTime receptionTime);

}
