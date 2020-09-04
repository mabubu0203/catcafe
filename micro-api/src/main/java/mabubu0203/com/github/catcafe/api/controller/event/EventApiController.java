package mabubu0203.com.github.catcafe.api.controller.event;

import lombok.RequiredArgsConstructor;
import org.openapitools.api.EventApi;
import org.openapitools.model.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequiredArgsConstructor
public class EventApiController implements EventApi {

    @Override
    public CompletableFuture<ResponseEntity<PostObject>> eventCreate(String cats, @Valid EventCreate eventCreate) {
        return null;
    }

    @Override
    public CompletableFuture<ResponseEntity<Void>> eventDelete(String cats, Integer eventId, @NotNull @Valid Integer version) {
        return null;
    }

    @Override
    public CompletableFuture<ResponseEntity<EventDetail>> eventFind(String cats, Integer eventId) {
        return null;
    }

    @Override
    public CompletableFuture<ResponseEntity<List<EventDetail>>> eventSearch(String cats, @Valid List<Integer> storeIds) {
        return null;
    }

    @Override
    public CompletableFuture<ResponseEntity<PatchObject>> eventUpdate(String cats, Integer eventId, @Valid EventUpdate eventUpdate) {
        return null;
    }

}
