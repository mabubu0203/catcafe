package mabubu0203.com.github.catcafe.common.source.redis;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import mabubu0203.com.github.catcafe.common.source.redis.entity.BaseHash;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.scheduling.annotation.Async;

@NoRepositoryBean
public interface HashSource<D extends BaseHash, ID> extends CrudRepository<D, ID> {

  @Async
  default CompletableFuture<D> insert(D entity, Long expiration) {
    Optional.ofNullable(expiration).ifPresent(entity::setExpiration);
    return CompletableFuture.supplyAsync(() -> this.save(entity));
  }

}
