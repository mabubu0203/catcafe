package mabubu0203.com.github.catcafe.api.controller.notice;

import lombok.RequiredArgsConstructor;
import org.openapitools.api.NoticeApi;
import org.openapitools.model.InlineObject;
import org.openapitools.model.InlineObject1;
import org.openapitools.model.InlineResponse200;
import org.openapitools.model.InlineResponse2001;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class NoticeApiController implements NoticeApi {

    @Override
    public ResponseEntity<InlineResponse200> noticeCreate(String cats, @Valid InlineObject inlineObject) {
        return null;
    }

    @Override
    public ResponseEntity<Void> noticeDelete(String cats, Integer noticeId, @NotNull @Valid Integer version) {
        return null;
    }

    @Override
    public ResponseEntity<InlineResponse2001> noticeFind(String cats, Integer noticeId) {
        return null;
    }

    @Override
    public ResponseEntity<List<InlineResponse2001>> noticeSearch(String cats, @Valid List<Integer> store) {
        return null;
    }

    @Override
    public ResponseEntity<InlineResponse200> noticeUpdate(String cats, Integer noticeId, @Valid InlineObject1 inlineObject1) {
        return null;
    }

}
