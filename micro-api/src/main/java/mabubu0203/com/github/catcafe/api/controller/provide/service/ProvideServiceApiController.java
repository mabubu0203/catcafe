package mabubu0203.com.github.catcafe.api.controller.provide.service;

import lombok.RequiredArgsConstructor;
import org.openapitools.api.ProvideServiceApi;
import org.openapitools.model.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequiredArgsConstructor
public class ProvideServiceApiController implements ProvideServiceApi {

    @Override
    public CompletableFuture<ResponseEntity<PostObject>> provideServiceCreate(String cats, Integer storeId, @Valid ProvideServiceCreate provideServiceCreate) {
        return null;
    }

    @Override
    public CompletableFuture<ResponseEntity<Void>> provideServiceDelete(String cats, Integer storeId, Integer provideServiceId, @NotNull @Valid Integer version) {
        return null;
    }

    @Override
    public CompletableFuture<ResponseEntity<ProvideServiceFindResponse>> provideServiceFind(String cats, Integer storeId, Integer provideServiceId) {
        return null;
    }

    @Override
    public CompletableFuture<ResponseEntity<ProvideServiceSearchResponse>> provideServiceSearch(String cats, @Valid List<Integer> storeIds) {
        return null;
    }

    @Override
    public CompletableFuture<ResponseEntity<PatchObject>> provideServiceUpdate(String cats, Integer storeId, Integer provideServiceId, @Valid ProvideServiceUpdate provideServiceUpdate) {
        return null;
    }

}
