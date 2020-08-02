package mabubu0203.com.github.catcafe.api.controller.menu;

import lombok.RequiredArgsConstructor;
import org.openapitools.api.MenuApi;
import org.openapitools.model.InlineObject5;
import org.openapitools.model.InlineObject6;
import org.openapitools.model.InlineResponse200;
import org.openapitools.model.InlineResponse2001;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class MenuApiController implements MenuApi {

    @Override
    public ResponseEntity<InlineResponse200> menuCreate(String cats, Integer storeId, @Valid InlineObject5 inlineObject5) {
        return null;
    }

    @Override
    public ResponseEntity<Void> menuDelete(String cats, Integer storeId, Integer menuId, @NotNull @Valid Integer version) {
        return null;
    }

    @Override
    public ResponseEntity<InlineResponse2001> menuFind(String cats, Integer storeId, Integer menuId) {
        return null;
    }

    @Override
    public ResponseEntity<List<InlineResponse2001>> menuSearch(String cats, @Valid List<Integer> store) {
        return null;
    }

    @Override
    public ResponseEntity<InlineResponse200> menuUpdate(String cats, Integer storeId, Integer menuId, @Valid InlineObject6 inlineObject6) {
        return null;
    }

}
