package mabubu0203.com.github.catcafe.api.controller.store;

import lombok.RequiredArgsConstructor;
import org.openapitools.api.StoreApi;
import org.openapitools.model.InlineObject2;
import org.openapitools.model.InlineObject3;
import org.openapitools.model.InlineResponse200;
import org.openapitools.model.InlineResponse2001;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class StoreApiController implements StoreApi {

    @Override
    public ResponseEntity<InlineResponse200> storeCreate(String cats, @Valid InlineObject2 inlineObject2) {
        return null;
    }

    @Override
    public ResponseEntity<Void> storeDelete(String cats, Integer storeId, @NotNull @Valid Integer version) {
        return null;
    }

    @Override
    public ResponseEntity<InlineResponse2001> storeFind(String cats, Integer storeId) {
        return null;
    }

    @Override
    public ResponseEntity<List<InlineResponse2001>> storeSearch(String cats, @Valid List<Integer> store) {
        return null;
    }

    @Override
    public ResponseEntity<InlineResponse200> storeUpdate(String cats, Integer storeId, @Valid InlineObject3 inlineObject3) {
        return null;
    }

}
