package mabubu0203.com.github.catcafe.api.controller.cast.helper.response;

import mabubu0203.com.github.catcafe.api.controller.cast.service.model.output.CastSearchServiceOutput;
import mabubu0203.com.github.catcafe.common.controller.mapper.response.SearchResponseMapper;
import org.openapitools.model.CastCat;
import org.openapitools.model.CastDetail;
import org.openapitools.model.CastSearchResponse;

import java.util.stream.Collectors;

public class CastSearchResponseMapper implements SearchResponseMapper<CastSearchServiceOutput, CastSearchResponse> {

    @Override
    public CastSearchResponse apply(CastSearchServiceOutput castSearchServiceOutput) {
        var casts = castSearchServiceOutput.getCasts().stream()
                .map(this::convert)
                .collect(Collectors.toList());

        var result = new CastSearchResponse();
        result.setCasts(casts);
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
