package mabubu0203.com.github.catcafe.api.controller.store.helper.response;

import mabubu0203.com.github.catcafe.api.controller.store.service.model.output.StoreSearchServiceOutput;
import mabubu0203.com.github.catcafe.common.controller.mapper.response.SearchResponseMapper;
import org.openapitools.model.*;

public class StoreSearchResponseMapper implements SearchResponseMapper<StoreSearchServiceOutput, StoreSearchResponse> {

    @Override
    public StoreSearchResponse apply(StoreSearchServiceOutput storeSearchServiceOutput) {
        var result = new StoreSearchResponse();
        for (StoreSearchServiceOutput.StoreObject store : storeSearchServiceOutput.getStores()) {
            var detail = new StoreDetail();
            detail.setId(store.getId());
            detail.setName(store.getName());

            detail.setContact(new Contact());
            detail.setAddress(new Address());
            detail.setHours(new Hours());
            result.addStoresItem(detail);
        }
        return result;
    }

}
