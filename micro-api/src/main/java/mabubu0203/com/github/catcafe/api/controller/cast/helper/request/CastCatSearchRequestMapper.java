package mabubu0203.com.github.catcafe.api.controller.cast.helper.request;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import mabubu0203.com.github.catcafe.api.controller.cast.service.model.input.CastCatSearchServiceInput;
import mabubu0203.com.github.catcafe.common.controller.mapper.request.SearchRequestMapper;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class CastCatSearchRequestMapper implements SearchRequestMapper<CastCatSearchServiceInput> {

  private final String cats;
  private final List<Integer> castCatIds;
  private final Integer page;
  private final Integer size;

  @Override
  public Mono<CastCatSearchServiceInput> get() {

    return Mono.just(
        CastCatSearchServiceInput.builder()
            .cats(this.cats)
            .optCastCatIds(Optional.ofNullable(this.castCatIds))
            .optPage(Optional.ofNullable(this.page))
            .optSize(Optional.ofNullable(this.size))
            .optSortKeys(Optional.empty())
            .build());
  }

}
