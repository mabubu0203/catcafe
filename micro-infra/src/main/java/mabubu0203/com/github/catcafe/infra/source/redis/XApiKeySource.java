package mabubu0203.com.github.catcafe.infra.source.redis;

import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;
import mabubu0203.com.github.catcafe.common.source.redis.HashSource;
import mabubu0203.com.github.catcafe.infra.source.redis.dto.XApiKey;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

@Repository
public interface XApiKeySource extends HashSource<XApiKey, Integer> {

  @Async
  CompletableFuture<Stream<XApiKey>> findByToken(String token);

}
