package mabubu0203.com.github.catcafe.api.controller.faq;

import lombok.RequiredArgsConstructor;
import org.openapitools.api.FrequentlyAskedQuestionApi;
import org.openapitools.model.FaqDetail;
import org.openapitools.model.FaqUpdate;
import org.openapitools.model.PatchObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequiredArgsConstructor
public class FrequentlyAskedQuestionApiController implements FrequentlyAskedQuestionApi {

    @Override
    public CompletableFuture<ResponseEntity<Void>> faqDelete(String cats, Integer faqId, @NotNull @Valid Integer version) {
        return null;
    }

    @Override
    public CompletableFuture<ResponseEntity<FaqDetail>> faqFind(String cats, Integer faqId) {
        return null;
    }

    @Override
    public CompletableFuture<ResponseEntity<List<FaqDetail>>> faqSearch(String cats, @Valid List<Integer> storeIds) {
        return null;
    }

    @Override
    public CompletableFuture<ResponseEntity<PatchObject>> faqUpdate(String cats, Integer faqId, @Valid FaqUpdate faqUpdate) {
        return null;
    }

}
