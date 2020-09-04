package mabubu0203.com.github.catcafe.api.controller.cast.mapper.response;

import mabubu0203.com.github.catcafe.api.service.cast.model.output.CastSearchServiceOutput;
import mabubu0203.com.github.catcafe.common.controller.mapper.response.FindResponseMapper;
import org.openapitools.model.CastDetail;

public class CastFindResponseMapper implements FindResponseMapper<CastSearchServiceOutput, CastDetail> {

    @Override
    public CastDetail apply(CastSearchServiceOutput castSearchServiceOutput) {
        return this.search();
    }

    private CastDetail search() {
        var detail = new CastDetail();
        detail.setId(1L);

        return detail;
    }

}