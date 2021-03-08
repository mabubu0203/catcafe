package mabubu0203.com.github.catcafe.common.source.r2dbc;

import mabubu0203.com.github.catcafe.common.source.r2dbc.dto.BaseView;
import org.reactivestreams.Publisher;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@NoRepositoryBean
public interface ViewSource<D extends BaseView, ID> extends ReactiveCrudRepository<D, ID> {

  @Override
  default <S extends D> Mono<S> save(S entity) {
    throw new UnsupportedOperationException();
  }

  @Override
  default <S extends D> Flux<S> saveAll(Iterable<S> entities) {
    throw new UnsupportedOperationException();
  }

  @Override
  default <S extends D> Flux<S> saveAll(Publisher<S> entityStream) {
    throw new UnsupportedOperationException();
  }

  @Override
  default Mono<D> findById(ID id) {
    throw new UnsupportedOperationException();
  }

  @Override
  default Mono<D> findById(Publisher<ID> id) {
    throw new UnsupportedOperationException();
  }

  @Override
  default Mono<Boolean> existsById(ID id) {
    throw new UnsupportedOperationException();
  }

  @Override
  default Mono<Boolean> existsById(Publisher<ID> id) {
    throw new UnsupportedOperationException();
  }

  @Override
  default Flux<D> findAllById(Iterable<ID> ids) {
    throw new UnsupportedOperationException();
  }

  @Override
  default Flux<D> findAllById(Publisher<ID> idStream) {
    throw new UnsupportedOperationException();
  }

  @Override
  default Mono<Void> deleteById(ID id) {
    throw new UnsupportedOperationException();
  }

  @Override
  default Mono<Void> deleteById(Publisher<ID> id) {
    throw new UnsupportedOperationException();
  }

  @Override
  default Mono<Void> delete(D entity) {
    throw new UnsupportedOperationException();
  }

  @Override
  default Mono<Void> deleteAll(Iterable<? extends D> entities) {
    throw new UnsupportedOperationException();
  }

  @Override
  default Mono<Void> deleteAll(Publisher<? extends D> entityStream) {
    throw new UnsupportedOperationException();
  }

  @Override
  default Mono<Void> deleteAll() {
    throw new UnsupportedOperationException();
  }

}
