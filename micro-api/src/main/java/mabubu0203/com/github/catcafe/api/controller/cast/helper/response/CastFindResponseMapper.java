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
        var cast = castSearchServiceOutput.getCasts().get(0);

        var detail = new CastDetail();
        detail.setId(cast.getId());
        detail.setStoreId(cast.getStoreId());

        var castCatObject = cast.getCastCat();

        var castCat = new CastCat();
        castCat.setId(castCatObject.getId());
        castCat.setName(castCatObject.getName());
        detail.setCastCat(castCat);
        result.setCast(detail);

        return result;
    }

}