package mabubu0203.com.github.catcafe.api.service.frequently.asked.question.impl;

import lombok.RequiredArgsConstructor;
import mabubu0203.com.github.catcafe.api.service.frequently.asked.question.FrequentlyAskedQuestionSearchService;
import mabubu0203.com.github.catcafe.api.service.frequently.asked.question.model.input.FrequentlyAskedQuestionSearchServiceInput;
import mabubu0203.com.github.catcafe.api.service.frequently.asked.question.model.output.FrequentlyAskedQuestionSearchServiceOutput;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class FrequentlyAskedQuestionSearchServiceImpl implements FrequentlyAskedQuestionSearchService {

    @Override
    public CompletableFuture<FrequentlyAskedQuestionSearchServiceOutput> promise(FrequentlyAskedQuestionSearchServiceInput input) {
        return CompletableFuture.completedFuture(new FrequentlyAskedQuestionSearchServiceOutput());
    }

}
