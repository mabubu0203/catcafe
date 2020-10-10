package mabubu0203.com.github.catcafe.api.controller.cast.helper.response;

import mabubu0203.com.github.catcafe.api.controller.cast.service.model.output.CastSearchServiceOutput;
import mabubu0203.com.github.catcafe.common.controller.mapper.response.FindResponseMapper;
import org.openapitools.model.CastCat;
import org.openapitools.model.CastDetail;
import org.openapitools.model.CastFindResponse;

public class CastFindResponseMapper implements FindResponseMapper<CastSearchServiceOutput, CastFindResponse> {

    @Override
    public CastFindResponse apply(CastSearchServiceOutput castSearchServiceOutput) {
        var result = new CastFindResponse();
        result.setCast(this.convert(castSearchServiceOutput.getCasts().get(0)));
        return result;
    }

    private CastDetail convert(CastSearchServiceOutput.CastObject cast) {
        var detail = new CastDetail();
        detail.setId(cast.getId());
        detail.setStoreId(cast.getStoreId());
        detail.setCastCat(this.convert(cast.getCastCat()));
        return detail;
    }

    private CastCat convert(CastSearchServiceOutput.CastCatObject castCat) {
        var detail = new CastCat();
        detail.setId(castCat.getId());
        detail.setName(castCat.getName());
        return detail;
    }
}