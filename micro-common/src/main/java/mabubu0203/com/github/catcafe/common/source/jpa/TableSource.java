package mabubu0203.com.github.catcafe.common.source.jpa;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;
import mabubu0203.com.github.catcafe.common.source.jpa.entity.BaseTable;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.scheduling.annotation.Async;

@NoRepositoryBean
public interface TableSource<D extends BaseTable, ID> extends JpaRepository<D, ID>,
    JpaSpecificationExecutor<D> {

  @Async
  default CompletableFuture<Optional<D>> findOne(D entity) {
    return CompletableFuture
        .supplyAsync(() -> entity)
        .thenApply(Example::of)
        .thenApply(this::findOne);
  }

  @Async
  default CompletableFuture<Stream<D>> searchStream(Specification<D> spec, Pageable pageable) {
    return this.search(spec, pageable).thenApply(Page::stream);
  }

  @Async
  default CompletableFuture<Page<D>> search(Specification<D> spec, Pageable pageable) {
    return CompletableFuture.supplyAsync(() -> findAll(spec, pageable));
  }

  @Async
  default CompletableFuture<D> insert(D entity, LocalDateTime localDateTime) {
    entity.setCreatedDateTime(localDateTime);
    entity.setVersion(0);
    return CompletableFuture.supplyAsync(() -> save(entity));
  }

  @Async
  default CompletableFuture<D> update(D entity, LocalDateTime localDateTime) {
    entity.setUpdatedDateTime(localDateTime);
    return CompletableFuture.supplyAsync(() -> save(entity));
  }

  @Async
  default CompletableFuture<D> logicalDelete(D entity, LocalDateTime localDateTime) {
    entity.setDeletedDateTime(localDateTime);
    entity.setDeletedFlag(true);
    return CompletableFuture.supplyAsync(() -> save(entity));
  }

}