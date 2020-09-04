package mabubu0203.com.github.catcafe.api.controller.cast.mapper.response;

import mabubu0203.com.github.catcafe.api.service.cast.model.output.CastSearchServiceOutput;
import mabubu0203.com.github.catcafe.common.controller.mapper.response.SearchResponseMapper;
import org.openapitools.model.CastDetail;
import org.openapitools.model.CastSearchResponse;

import java.util.ArrayList;

public class CastSearchResponseMapper implements SearchResponseMapper<CastSearchServiceOutput, CastSearchResponse> {

    @Override
    public CastSearchResponse apply(CastSearchServiceOutput castSearchServiceOutput) {
        return this.search();
    }

    private CastSearchResponse search() {
        var resultRest = new ArrayList<CastDetail>();
        var detail = new CastDetail();
        detail.setId(1L);
        resultRest.add(detail);

        var result = new CastSearchResponse();
        result.setCasts(resultRest);
        return result;
    }

}
