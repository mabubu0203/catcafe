package mabubu0203.com.github.catcafe.api.controller.cast.mapper.response;

import mabubu0203.com.github.catcafe.api.service.cast.model.output.CastSearchServiceOutput;
import mabubu0203.com.github.catcafe.common.controller.mapper.response.SearchResponseMapper;
import org.openapitools.model.CastCat;
import org.openapitools.model.CastDetail;
import org.openapitools.model.CastSearchResponse;

public class CastSearchResponseMapper implements SearchResponseMapper<CastSearchServiceOutput, CastSearchResponse> {

    @Override
    public CastSearchResponse apply(CastSearchServiceOutput castSearchServiceOutput) {
        return this.search();
    }

    private CastSearchResponse search() {
        var detail = new CastDetail();
        detail.setId(1);
        detail.setStoreMemo("店舗メモ");

        var castCat = new CastCat();
        castCat.setName("ねこちゃん");
        castCat.setSex("雄");
        detail.setCastCat(castCat);

        var result = new CastSearchResponse();
        result.addCastsItem(detail);
        return result;
    }

}
