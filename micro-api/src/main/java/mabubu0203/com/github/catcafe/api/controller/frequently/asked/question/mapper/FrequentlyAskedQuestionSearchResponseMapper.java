package mabubu0203.com.github.catcafe.api.controller.frequently.asked.question.mapper;

import mabubu0203.com.github.catcafe.api.service.frequently.asked.question.model.FrequentlyAskedQuestionSearchServiceOutput;
import org.openapitools.model.FrequentlyAskedQuestionDetail;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class FrequentlyAskedQuestionSearchResponseMapper implements Function<FrequentlyAskedQuestionSearchServiceOutput, List<FrequentlyAskedQuestionDetail>> {

    @Override
    public List<FrequentlyAskedQuestionDetail> apply(FrequentlyAskedQuestionSearchServiceOutput frequentlyAskedQuestionSearchServiceOutput) {
        return this.search();
    }

    List<FrequentlyAskedQuestionDetail> search() {
        var resultRest = new ArrayList<FrequentlyAskedQuestionDetail>();
        var detail = new FrequentlyAskedQuestionDetail();
        detail.setId(1L);
        resultRest.add(detail);

        return resultRest;
    }

}
