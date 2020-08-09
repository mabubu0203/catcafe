package mabubu0203.com.github.catcafe.api.controller.frequently.asked.question;

import lombok.RequiredArgsConstructor;
import org.openapitools.api.FrequentlyAskedQuestionApi;
import org.openapitools.model.*;
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
    public CompletableFuture<ResponseEntity<PostObject>> frequentlyAskedQuestionCreate(String cats, @Valid FrequentlyAskedQuestionCreate frequentlyAskedQuestionCreate) {
        return null;
    }

    @Override
    public CompletableFuture<ResponseEntity<Void>> frequentlyAskedQuestionDelete(String cats, Integer faqId, @NotNull @Valid Integer version) {
        return null;
    }

    @Override
    public CompletableFuture<ResponseEntity<FrequentlyAskedQuestionDetail>> frequentlyAskedQuestionFind(String cats, Integer faqId) {
        return null;
    }

    @Override
    public CompletableFuture<ResponseEntity<List<FrequentlyAskedQuestionDetail>>> frequentlyAskedQuestionSearch(String cats, @Valid List<Integer> storeIds) {
        return null;
    }

    @Override
    public CompletableFuture<ResponseEntity<PatchObject>> frequentlyAskedQuestionUpdate(String cats, Integer faqId, @Valid FrequentlyAskedQuestionUpdate frequentlyAskedQuestionUpdate) {
        return null;
    }

}
