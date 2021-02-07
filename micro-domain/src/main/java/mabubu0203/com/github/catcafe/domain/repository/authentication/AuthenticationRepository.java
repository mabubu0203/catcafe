package mabubu0203.com.github.catcafe.domain.repository.authentication;

import mabubu0203.com.github.catcafe.domain.entity.authentication.XApiKeyEntity;
import mabubu0203.com.github.catcafe.domain.entity.authentication.XApiKeySearchConditions;

import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

public interface AuthenticationRepository {

    CompletableFuture<Stream<XApiKeyEntity>> search(XApiKeySearchConditions searchConditions);

    CompletableFuture<XApiKeyEntity> generate(XApiKeyEntity xApiKey, LocalDateTime receptionTime);

}
