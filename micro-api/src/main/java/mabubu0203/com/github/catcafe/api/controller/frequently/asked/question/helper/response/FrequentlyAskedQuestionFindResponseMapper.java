package mabubu0203.com.github.catcafe.api.controller.frequently.asked.question.helper.response;

import mabubu0203.com.github.catcafe.api.controller.frequently.asked.question.service.model.output.FrequentlyAskedQuestionSearchServiceOutput;
import mabubu0203.com.github.catcafe.common.controller.mapper.response.FindResponseMapper;
import org.openapitools.model.FrequentlyAskedQuestionDetail;
import org.openapitools.model.FrequentlyAskedQuestionFindResponse;

public class FrequentlyAskedQuestionFindResponseMapper implements
    FindResponseMapper<FrequentlyAskedQuestionSearchServiceOutput, FrequentlyAskedQuestionFindResponse> {

  @Override
  public FrequentlyAskedQuestionFindResponse apply(
      FrequentlyAskedQuestionSearchServiceOutput frequentlyAskedQuestionSearchServiceOutput) {
    return this.search();
  }

  private FrequentlyAskedQuestionFindResponse search() {
    var detail = new FrequentlyAskedQuestionDetail();
    detail.setId(1);

    var result = new FrequentlyAskedQuestionFindResponse();
    result.setFrequentlyAskedQuestion(detail);
    return result;
  }

}
