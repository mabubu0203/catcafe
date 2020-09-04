package mabubu0203.com.github.catcafe.api.controller.frequently.asked.question.mapper.request;

import lombok.RequiredArgsConstructor;
import mabubu0203.com.github.catcafe.api.service.frequently.asked.question.model.input.FrequentlyAskedQuestionSearchServiceInput;
import mabubu0203.com.github.catcafe.common.controller.mapper.request.SearchRequestMapper;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class FrequentlyAskedQuestionSearchRequestMapper implements SearchRequestMapper<FrequentlyAskedQuestionSearchServiceInput> {

    private final String cats;
    private final List<Integer> storeIds;

    @Override
    public Optional<FrequentlyAskedQuestionSearchServiceInput> get() {
        return Optional.of(new FrequentlyAskedQuestionSearchServiceInput());
    }

}
