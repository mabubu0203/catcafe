package mabubu0203.com.github.catcafe.api.controller.store.helper.response;

import mabubu0203.com.github.catcafe.api.controller.store.service.model.output.StoreSearchServiceOutput;
import mabubu0203.com.github.catcafe.common.controller.mapper.response.SearchResponseMapper;
import org.openapitools.model.*;

import java.util.stream.Collectors;

public class StoreSearchResponseMapper implements SearchResponseMapper<StoreSearchServiceOutput, StoreSearchResponse> {

    @Override
    public StoreSearchResponse apply(StoreSearchServiceOutput storeSearchServiceOutput) {
        var stores = storeSearchServiceOutput.getStores().stream()
                .map(this::convert)
                .collect(Collectors.toList());

        var result = new StoreSearchResponse();
        result.setStores(stores);
        return result;
    }

    private StoreDetail convert(StoreSearchServiceOutput.StoreObject store) {
        var detail = new StoreDetail();
        detail.setId(store.getId());
        detail.setName(store.getName());

        detail.setContact(new Contact());
        detail.setAddress(new Address());
        detail.setHours(new Hours());
        return detail;
    }

}
