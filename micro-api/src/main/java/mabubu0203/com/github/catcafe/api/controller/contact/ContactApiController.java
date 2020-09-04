package mabubu0203.com.github.catcafe.api.controller.contact;

import lombok.RequiredArgsConstructor;
import org.openapitools.api.ContactApi;
import org.openapitools.model.ContactCreate;
import org.openapitools.model.ContactFindResponse;
import org.openapitools.model.ContactSearchResponse;
import org.openapitools.model.PostObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequiredArgsConstructor
public class ContactApiController implements ContactApi {

    @Override
    public CompletableFuture<ResponseEntity<PostObject>> contactCreate(String cats, @Valid ContactCreate contactCreate) {
        return null;
    }

    @Override
    public CompletableFuture<ResponseEntity<ContactFindResponse>> contactFind(String cats, Integer contactId) {
        return null;
    }

    @Override
    public CompletableFuture<ResponseEntity<ContactSearchResponse>> contactSearch(String cats, @Valid List<Integer> storeIds) {
        return null;
    }

}
