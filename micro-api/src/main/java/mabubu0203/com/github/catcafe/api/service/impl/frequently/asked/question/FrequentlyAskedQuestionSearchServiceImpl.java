package mabubu0203.com.github.catcafe.api.service.impl.frequently.asked.question;

import lombok.RequiredArgsConstructor;
import mabubu0203.com.github.catcafe.api.controller.frequently.asked.question.service.FrequentlyAskedQuestionSearchService;
import mabubu0203.com.github.catcafe.api.controller.frequently.asked.question.service.model.input.FrequentlyAskedQuestionSearchServiceInput;
import mabubu0203.com.github.catcafe.api.controller.frequently.asked.question.service.model.output.FrequentlyAskedQuestionSearchServiceOutput;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class FrequentlyAskedQuestionSearchServiceImpl implements
    FrequentlyAskedQuestionSearchService {

  @Override
  @Transactional(readOnly = true)
  public Mono<FrequentlyAskedQuestionSearchServiceOutput> action(
      FrequentlyAskedQuestionSearchServiceInput input) {
    return null;
//    return CompletableFuture.completedFuture(new FrequentlyAskedQuestionSearchServiceOutput());
  }

}
