package mabubu0203.com.github.catcafe.api.service.frequently.asked.question.impl;

import lombok.RequiredArgsConstructor;
import mabubu0203.com.github.catcafe.api.service.frequently.asked.question.FrequentlyAskedQuestionSearchService;
import org.openapitools.model.FrequentlyAskedQuestionDetail;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FrequentlyAskedQuestionSearchServiceImpl implements FrequentlyAskedQuestionSearchService {

    @Override
    public List<FrequentlyAskedQuestionDetail> search() {
        var resultRest = new ArrayList<FrequentlyAskedQuestionDetail>();
        var detail = new FrequentlyAskedQuestionDetail();
        detail.setId(1L);
        resultRest.add(detail);

        return resultRest;
    }

}
