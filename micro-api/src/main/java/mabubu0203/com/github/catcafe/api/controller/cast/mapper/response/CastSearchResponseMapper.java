package mabubu0203.com.github.catcafe.api.controller.cast.mapper.response;

import mabubu0203.com.github.catcafe.api.service.cast.model.output.CastSearchServiceOutput;
import mabubu0203.com.github.catcafe.common.controller.mapper.response.SearchResponseMapper;
import org.openapitools.model.CastDetail;
import org.openapitools.model.CastSearchResponse;
import org.openapitools.model.CatCast;

public class CastSearchResponseMapper implements SearchResponseMapper<CastSearchServiceOutput, CastSearchResponse> {

    @Override
    public CastSearchResponse apply(CastSearchServiceOutput castSearchServiceOutput) {
        return this.search();
    }

    private CastSearchResponse search() {
        var detail = new CastDetail();
        detail.setId(1);
        detail.setStoreMemo("店舗メモ");

        var catCast = new CatCast();
        catCast.setName("ねこちゃん");
        catCast.setSex("雄");
        detail.setCatCast(catCast);

        var result = new CastSearchResponse();
        result.addCastsItem(detail);
        return result;
    }

}
