package mabubu0203.com.github.catcafe.api.controller.menu;

import lombok.RequiredArgsConstructor;
import org.openapitools.api.MenuApi;
import org.openapitools.model.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequiredArgsConstructor
public class MenuApiController implements MenuApi {

    @Override
    public CompletableFuture<ResponseEntity<PostObject>> menuCreate(String cats, Integer storeId, @Valid MenuCreate menuCreate) {
        return null;
    }

    @Override
    public CompletableFuture<ResponseEntity<Void>> menuDelete(String cats, Integer storeId, Integer menuId, @NotNull @Valid Integer version) {
        return null;
    }

    @Override
    public CompletableFuture<ResponseEntity<MenuDetail>> menuFind(String cats, Integer storeId, Integer menuId) {
        return null;
    }

    @Override
    public CompletableFuture<ResponseEntity<List<MenuDetail>>> menuSearch(String cats, @Valid List<Integer> storeIds) {
        return null;
    }

    @Override
    public CompletableFuture<ResponseEntity<PatchObject>> menuUpdate(String cats, Integer storeId, Integer menuId, @Valid MenuUpdate menuUpdate) {
        return null;
    }

}
