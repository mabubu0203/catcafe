package mabubu0203.com.github.catcafe.common.source.jpa;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;
import mabubu0203.com.github.catcafe.common.source.jpa.dto.BaseView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.scheduling.annotation.Async;

@NoRepositoryBean
public interface ViewSource<D extends BaseView, ID> extends JpaRepository<D, ID>,
    JpaSpecificationExecutor<D> {

  @Async
  default CompletableFuture<Stream<D>> searchStream(Specification<D> spec, Pageable pageable) {
    return this.search(spec, pageable).thenApply(Page::stream);
  }

  @Async
  default CompletableFuture<Page<D>> search(Specification<D> spec, Pageable pageable) {
    return CompletableFuture.supplyAsync(() -> findAll(spec, pageable));
  }

  @Override
  default D getOne(ID id) {
    throw new UnsupportedOperationException();
  }

  @Override
  default Optional<D> findById(ID id) {
    throw new UnsupportedOperationException();
  }

  @Override
  default boolean existsById(ID id) {
    throw new UnsupportedOperationException();
  }

  @Override
  default void deleteById(ID id) {
    throw new UnsupportedOperationException();
  }

  @Override
  default <S extends D> S save(S entity) {
    throw new UnsupportedOperationException();
  }

  @Override
  default <S extends D> S saveAndFlush(S entity) {
    throw new UnsupportedOperationException();
  }

  @Override
  default <S extends D> List<S> saveAll(Iterable<S> entities) {
    throw new UnsupportedOperationException();
  }

  @Override
  default void delete(D entity) {
    throw new UnsupportedOperationException();
  }

  @Override
  default void deleteAll(Iterable<? extends D> entities) {
    throw new UnsupportedOperationException();
  }

  @Override
  default void deleteAll() {
    throw new UnsupportedOperationException();
  }

  @Override
  default void deleteInBatch(Iterable<D> entities) {
    throw new UnsupportedOperationException();
  }

  @Override
  default void deleteAllInBatch() {
    throw new UnsupportedOperationException();
  }

  @Override
  default void flush() {
    throw new UnsupportedOperationException();
  }

}
