package mabubu0203.com.github.catcafe.api.controller.store.mapper.response;

import mabubu0203.com.github.catcafe.api.service.store.model.output.StoreSearchServiceOutput;
import mabubu0203.com.github.catcafe.common.controller.mapper.response.SearchResponseMapper;
import org.openapitools.model.StoreDetail;
import org.openapitools.model.StoreSearchResponse;

public class StoreSearchResponseMapper implements SearchResponseMapper<StoreSearchServiceOutput, StoreSearchResponse> {

    @Override
    public StoreSearchResponse apply(StoreSearchServiceOutput storeSearchServiceOutput) {
        var result = new StoreSearchResponse();
        for (StoreSearchServiceOutput.StoreObject store : storeSearchServiceOutput.getStores()) {
            var detail = new StoreDetail();
            detail.setId(store.getId());
            detail.setName(store.getName());
            result.addStoresItem(detail);
        }
        return result;
    }

}
