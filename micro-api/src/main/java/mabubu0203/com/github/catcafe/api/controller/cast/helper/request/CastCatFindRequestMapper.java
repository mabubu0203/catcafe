package mabubu0203.com.github.catcafe.api.controller.cast.helper.request;

import java.util.ArrayList;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import mabubu0203.com.github.catcafe.api.controller.cast.service.model.input.CastCatSearchServiceInput;
import mabubu0203.com.github.catcafe.common.controller.mapper.request.FindRequestMapper;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class CastCatFindRequestMapper implements FindRequestMapper<CastCatSearchServiceInput> {

  private final String cats;
  private final Integer castCatId;

  @Override
  public Mono<CastCatSearchServiceInput> get() {

    var castCatIds = new ArrayList<Integer>();
    castCatIds.add(this.castCatId);
    return Mono.just(
        CastCatSearchServiceInput.builder()
            .cats(this.cats)
            .optCastCatIds(Optional.of(castCatIds))
            .optPage(Optional.empty())
            .optSize(Optional.empty())
            .optSortKeys(Optional.empty())
            .build());
  }

}
