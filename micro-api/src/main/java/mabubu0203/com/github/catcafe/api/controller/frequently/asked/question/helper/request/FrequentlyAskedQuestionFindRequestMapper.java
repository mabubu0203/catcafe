package mabubu0203.com.github.catcafe.api.controller.frequently.asked.question.helper.request;

import lombok.RequiredArgsConstructor;
import mabubu0203.com.github.catcafe.api.controller.frequently.asked.question.service.model.input.FrequentlyAskedQuestionSearchServiceInput;
import mabubu0203.com.github.catcafe.common.controller.mapper.request.FindRequestMapper;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class FrequentlyAskedQuestionFindRequestMapper implements FindRequestMapper<FrequentlyAskedQuestionSearchServiceInput> {

    private final String cats;
    private final Integer faqId;

    @Override
    public Mono<FrequentlyAskedQuestionSearchServiceInput> get() {
        return Mono.just(new FrequentlyAskedQuestionSearchServiceInput());
    }

}
