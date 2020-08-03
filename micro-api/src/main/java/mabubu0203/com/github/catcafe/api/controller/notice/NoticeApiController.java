package mabubu0203.com.github.catcafe.api.controller.notice;

import lombok.RequiredArgsConstructor;
import org.openapitools.api.NoticeApi;
import org.openapitools.model.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequiredArgsConstructor
public class NoticeApiController implements NoticeApi {

    @Override
    public CompletableFuture<ResponseEntity<PostObject>> noticeCreate(String cats, @Valid NoticeCreate noticeCreate) {
        return null;
    }

    @Override
    public CompletableFuture<ResponseEntity<Void>> noticeDelete(String cats, Integer noticeId, @NotNull @Valid Integer version) {
        return null;
    }

    @Override
    public CompletableFuture<ResponseEntity<NoticeDetail>> noticeFind(String cats, Integer noticeId) {
        return null;
    }

    @Override
    public CompletableFuture<ResponseEntity<List<NoticeDetail>>> noticeSearch(String cats, @Valid List<Integer> storeIds) {
        return null;
    }

    @Override
    public CompletableFuture<ResponseEntity<PatchObject>> noticeUpdate(String cats, Integer noticeId, @Valid NoticeUpdate noticeUpdate) {
        return null;
    }

}
