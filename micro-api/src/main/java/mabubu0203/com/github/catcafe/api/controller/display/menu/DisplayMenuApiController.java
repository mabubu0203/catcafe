package mabubu0203.com.github.catcafe.api.controller.display.menu;

import lombok.RequiredArgsConstructor;
import org.openapitools.api.DisplayMenuApi;
import org.openapitools.model.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequiredArgsConstructor
public class DisplayMenuApiController implements DisplayMenuApi {

    @Override
    public CompletableFuture<ResponseEntity<PostObject>> displayMenuCreate(String cats, Integer storeId, @Valid DisplayMenuCreate displayMenuCreate) {
        return null;
    }

    @Override
    public CompletableFuture<ResponseEntity<Void>> displayMenuDelete(String cats, Integer storeId, Integer displayMenuId, @NotNull @Valid Integer version) {
        return null;
    }

    @Override
    public CompletableFuture<ResponseEntity<DisplayMenuFindResponse>> displayMenuFind(String cats, Integer storeId, Integer displayMenuId) {
        return null;
    }

    @Override
    public CompletableFuture<ResponseEntity<DisplayMenuSearchResponse>> displayMenuSearch(String cats, @Valid List<Integer> storeIds) {
        return null;
    }

    @Override
    public CompletableFuture<ResponseEntity<PatchObject>> displayMenuUpdate(String cats, Integer storeId, Integer displayMenuId, @Valid DisplayMenuUpdate displayMenuUpdate) {
        return null;
    }

}
