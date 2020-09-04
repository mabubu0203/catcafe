package mabubu0203.com.github.catcafe.api.controller.frequently.asked.question.mapper.response;

import mabubu0203.com.github.catcafe.api.service.frequently.asked.question.model.output.FrequentlyAskedQuestionSearchServiceOutput;
import mabubu0203.com.github.catcafe.common.controller.mapper.response.SearchResponseMapper;
import org.openapitools.model.FrequentlyAskedQuestionDetail;

import java.util.ArrayList;
import java.util.List;

public class FrequentlyAskedQuestionSearchResponseMapper implements SearchResponseMapper<FrequentlyAskedQuestionSearchServiceOutput, List<FrequentlyAskedQuestionDetail>> {

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
