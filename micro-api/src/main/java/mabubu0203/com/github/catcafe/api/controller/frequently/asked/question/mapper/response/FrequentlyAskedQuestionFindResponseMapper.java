package mabubu0203.com.github.catcafe.api.controller.frequently.asked.question.mapper.response;

import mabubu0203.com.github.catcafe.api.service.frequently.asked.question.model.output.FrequentlyAskedQuestionSearchServiceOutput;
import mabubu0203.com.github.catcafe.common.controller.mapper.response.FindResponseMapper;
import org.openapitools.model.FrequentlyAskedQuestionDetail;

public class FrequentlyAskedQuestionFindResponseMapper implements FindResponseMapper<FrequentlyAskedQuestionSearchServiceOutput, FrequentlyAskedQuestionDetail> {

    @Override
    public FrequentlyAskedQuestionDetail apply(FrequentlyAskedQuestionSearchServiceOutput frequentlyAskedQuestionSearchServiceOutput) {
        return this.search();
    }

    FrequentlyAskedQuestionDetail search() {
        var detail = new FrequentlyAskedQuestionDetail();
        detail.setId(1L);
        return detail;
    }

}
