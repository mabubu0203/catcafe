package mabubu0203.com.github.catcafe.api.controller.store;

import lombok.RequiredArgsConstructor;
import org.openapitools.api.StoreApi;
import org.openapitools.model.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequiredArgsConstructor
public class StoreApiController implements StoreApi {

    @Override
    public CompletableFuture<ResponseEntity<PostObject>> storeCreate(String cats, @Valid StoreCreate storeCreate) {
        return null;
    }

    @Override
    public CompletableFuture<ResponseEntity<Void>> storeDelete(String cats, Integer storeId, @NotNull @Valid Integer version) {
        return null;
    }

    @Override
    public CompletableFuture<ResponseEntity<StoreFindResponse>> storeFind(String cats, Integer storeId) {
        return null;
    }

    @Override
    public CompletableFuture<ResponseEntity<StoreSearchResponse>> storeSearch(String cats, @Valid List<Integer> storeIds) {
        return null;
    }

    @Override
    public CompletableFuture<ResponseEntity<PatchObject>> storeUpdate(String cats, Integer storeId, @Valid StoreUpdate storeUpdate) {
        return null;
    }

}
