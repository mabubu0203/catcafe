package mabubu0203.com.github.catcafe.api.controller.frequently.asked.question.mapper;

import lombok.RequiredArgsConstructor;
import mabubu0203.com.github.catcafe.api.service.frequently.asked.question.model.FrequentlyAskedQuestionSearchServiceInput;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

@RequiredArgsConstructor
public class FrequentlyAskedQuestionSearchRequestMapper implements Supplier<Optional<FrequentlyAskedQuestionSearchServiceInput>> {

    private final String cats;
    private final List<Integer> storeIds;

    @Override
    public Optional<FrequentlyAskedQuestionSearchServiceInput> get() {
        return Optional.of(new FrequentlyAskedQuestionSearchServiceInput());
    }

}
