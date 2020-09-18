package mabubu0203.com.github.catcafe.api.controller.frequently.asked.question.helper.response;

import mabubu0203.com.github.catcafe.api.controller.frequently.asked.question.service.model.output.FrequentlyAskedQuestionSearchServiceOutput;
import mabubu0203.com.github.catcafe.common.controller.mapper.response.SearchResponseMapper;
import org.openapitools.model.FrequentlyAskedQuestionDetail;
import org.openapitools.model.FrequentlyAskedQuestionSearchResponse;

public class FrequentlyAskedQuestionSearchResponseMapper implements SearchResponseMapper<FrequentlyAskedQuestionSearchServiceOutput, FrequentlyAskedQuestionSearchResponse> {

    @Override
    public FrequentlyAskedQuestionSearchResponse apply(FrequentlyAskedQuestionSearchServiceOutput frequentlyAskedQuestionSearchServiceOutput) {
        return this.search();
    }

    private FrequentlyAskedQuestionSearchResponse search() {
        var detail = new FrequentlyAskedQuestionDetail();
        detail.setId(1);
        detail.setCategory("料金に関する");

        var result = new FrequentlyAskedQuestionSearchResponse();
        result.addFrequentlyAskedQuestionsItem(detail);
        return result;
    }

}
