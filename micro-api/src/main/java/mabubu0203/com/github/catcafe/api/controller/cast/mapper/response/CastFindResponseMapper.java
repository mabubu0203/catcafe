package mabubu0203.com.github.catcafe.api.controller.cast.mapper.response;

import mabubu0203.com.github.catcafe.api.service.cast.model.output.CastSearchServiceOutput;
import mabubu0203.com.github.catcafe.common.controller.mapper.response.FindResponseMapper;
import org.openapitools.model.CastDetail;
import org.openapitools.model.CastFindResponse;

public class CastFindResponseMapper implements FindResponseMapper<CastSearchServiceOutput, CastFindResponse> {

    @Override
    public CastFindResponse apply(CastSearchServiceOutput castSearchServiceOutput) {
        return this.search();
    }

    private CastFindResponse search() {
        var detail = new CastDetail();
        detail.setId(1L);

        var result = new CastFindResponse();
        result.setCast(detail);
        return result;
    }

}