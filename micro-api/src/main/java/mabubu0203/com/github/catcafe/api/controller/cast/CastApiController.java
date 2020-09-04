package mabubu0203.com.github.catcafe.api.controller.cast;

import lombok.RequiredArgsConstructor;
import org.openapitools.api.CastApi;
import org.openapitools.model.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequiredArgsConstructor
public class CastApiController implements CastApi {

    @Override
    public CompletableFuture<ResponseEntity<PostObject>> castCreate(String cats, Integer storeId, @Valid CastCreate castCreate) {
        return null;
    }

    @Override
    public CompletableFuture<ResponseEntity<Void>> castDelete(String cats, Integer storeId, Integer castId, @NotNull @Valid Integer version) {
        return null;
    }

    @Override
    public CompletableFuture<ResponseEntity<CastDetail>> castFind(String cats, Integer storeId, Integer castId) {
        return null;
    }

    @Override
    public CompletableFuture<ResponseEntity<List<CastDetail>>> castSearch(String cats, @Valid List<Integer> storeIds) {
        return null;
    }

    @Override
    public CompletableFuture<ResponseEntity<PatchObject>> castUpdate(String cats, Integer storeId, Integer castId, @Valid CastUpdate castUpdate) {
        return null;
    }

}
