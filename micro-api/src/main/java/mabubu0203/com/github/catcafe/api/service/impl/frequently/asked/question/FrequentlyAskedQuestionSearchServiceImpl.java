package mabubu0203.com.github.catcafe.api.service.impl.frequently.asked.question;

import java.util.concurrent.CompletableFuture;
import lombok.RequiredArgsConstructor;
import mabubu0203.com.github.catcafe.api.controller.frequently.asked.question.service.FrequentlyAskedQuestionSearchService;
import mabubu0203.com.github.catcafe.api.controller.frequently.asked.question.service.model.input.FrequentlyAskedQuestionSearchServiceInput;
import mabubu0203.com.github.catcafe.api.controller.frequently.asked.question.service.model.output.FrequentlyAskedQuestionSearchServiceOutput;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FrequentlyAskedQuestionSearchServiceImpl implements
    FrequentlyAskedQuestionSearchService {

  @Override
  @Async
  @Transactional(readOnly = true)
  public CompletableFuture<FrequentlyAskedQuestionSearchServiceOutput> promise(
      FrequentlyAskedQuestionSearchServiceInput input) {
    return CompletableFuture.completedFuture(new FrequentlyAskedQuestionSearchServiceOutput());
  }

}
