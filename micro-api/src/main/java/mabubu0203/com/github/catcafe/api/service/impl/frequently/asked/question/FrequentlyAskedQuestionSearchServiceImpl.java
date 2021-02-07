package mabubu0203.com.github.catcafe.api.service.impl.frequently.asked.question;

import java.util.concurrent.CompletableFuture;
import lombok.RequiredArgsConstructor;
import mabubu0203.com.github.catcafe.api.controller.frequently.asked.question.service.FrequentlyAskedQuestionSearchService;
import mabubu0203.com.github.catcafe.api.controller.frequently.asked.question.service.model.input.FrequentlyAskedQuestionSearchServiceInput;
import mabubu0203.com.github.catcafe.api.controller.frequently.asked.question.service.model.output.FrequentlyAskedQuestionSearchServiceOutput;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FrequentlyAskedQuestionSearchServiceImpl implements
    FrequentlyAskedQuestionSearchService {

  @Override
  public CompletableFuture<FrequentlyAskedQuestionSearchServiceOutput> promise(
      FrequentlyAskedQuestionSearchServiceInput input) {
    return CompletableFuture.completedFuture(new FrequentlyAskedQuestionSearchServiceOutput());
  }

}
