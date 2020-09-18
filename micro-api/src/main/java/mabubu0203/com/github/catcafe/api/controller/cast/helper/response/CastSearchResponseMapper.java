package mabubu0203.com.github.catcafe.api.controller.cast.helper.response;

import mabubu0203.com.github.catcafe.api.controller.cast.service.model.output.CastSearchServiceOutput;
import mabubu0203.com.github.catcafe.common.controller.mapper.response.SearchResponseMapper;
import org.openapitools.model.CastCat;
import org.openapitools.model.CastDetail;
import org.openapitools.model.CastSearchResponse;

public class CastSearchResponseMapper implements SearchResponseMapper<CastSearchServiceOutput, CastSearchResponse> {

    @Override
    public CastSearchResponse apply(CastSearchServiceOutput castSearchServiceOutput) {
        var result = new CastSearchResponse();
        for (CastSearchServiceOutput.CastObject cast : castSearchServiceOutput.getCasts()) {
            var detail = new CastDetail();
            detail.setId(cast.getId());
            detail.setStoreId(cast.getStoreId());

            var castCatObject = cast.getCastCat();

            var castCat = new CastCat();
            castCat.setId(castCatObject.getId());
            castCat.setName(castCatObject.getName());
            detail.setCastCat(castCat);
            result.addCastsItem(detail);
        }
        return result;
    }

}
