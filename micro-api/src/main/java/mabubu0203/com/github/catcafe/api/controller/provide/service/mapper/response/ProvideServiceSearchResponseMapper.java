package mabubu0203.com.github.catcafe.api.controller.provide.service.mapper.response;

import mabubu0203.com.github.catcafe.api.service.provide.service.model.output.ProvideServiceSearchServiceOutput;
import mabubu0203.com.github.catcafe.common.controller.mapper.response.SearchResponseMapper;
import org.openapitools.model.ProvideServiceDetail;
import org.openapitools.model.ProvideServiceSearchResponse;

public class ProvideServiceSearchResponseMapper implements SearchResponseMapper<ProvideServiceSearchServiceOutput, ProvideServiceSearchResponse> {

    @Override
    public ProvideServiceSearchResponse apply(ProvideServiceSearchServiceOutput provideServiceSearchServiceOutput) {
        return this.search();
    }

    private ProvideServiceSearchResponse search() {
        var detail = new ProvideServiceDetail();
        detail.setId(1);
        detail.setName("おやつ");
        detail.setPrice("3200");

        var result = new ProvideServiceSearchResponse();
        result.addProvideServicesItem(detail);
        return result;
    }

}
