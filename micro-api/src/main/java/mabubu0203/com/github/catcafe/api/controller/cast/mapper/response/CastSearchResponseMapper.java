package mabubu0203.com.github.catcafe.api.controller.cast.mapper.response;

import mabubu0203.com.github.catcafe.api.service.cast.model.output.CastSearchServiceOutput;
import mabubu0203.com.github.catcafe.common.controller.mapper.response.SearchResponseMapper;
import org.openapitools.model.CastDetail;

import java.util.ArrayList;
import java.util.List;

public class CastSearchResponseMapper implements SearchResponseMapper<CastSearchServiceOutput, List<CastDetail>> {

    @Override
    public List<CastDetail> apply(CastSearchServiceOutput castSearchServiceOutput) {
        return this.search();
    }

    private List<CastDetail> search() {
        var resultRest = new ArrayList<CastDetail>();
        var detail = new CastDetail();
        detail.setId(1L);
        resultRest.add(detail);

        return resultRest;
    }

}
