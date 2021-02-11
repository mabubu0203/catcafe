package mabubu0203.com.github.catcafe.api.controller.frequently.asked.question.helper.request;

import java.util.List;
import lombok.RequiredArgsConstructor;
import mabubu0203.com.github.catcafe.api.controller.frequently.asked.question.service.model.input.FrequentlyAskedQuestionSearchServiceInput;
import mabubu0203.com.github.catcafe.common.controller.mapper.request.SearchRequestMapper;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class FrequentlyAskedQuestionSearchRequestMapper implements
    SearchRequestMapper<FrequentlyAskedQuestionSearchServiceInput> {

  private final String cats;
  private final List<Integer> storeIds;

  @Override
  public Mono<FrequentlyAskedQuestionSearchServiceInput> get() {
    return Mono.just(new FrequentlyAskedQuestionSearchServiceInput());
  }

}
