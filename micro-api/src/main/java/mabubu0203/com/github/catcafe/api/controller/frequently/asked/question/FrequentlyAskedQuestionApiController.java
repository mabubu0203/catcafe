package mabubu0203.com.github.catcafe.api.controller.frequently.asked.question;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import mabubu0203.com.github.catcafe.api.service.frequently.asked.question.FrequentlyAskedQuestionSearchService;
import org.openapitools.api.FrequentlyAskedQuestionApi;
import org.openapitools.model.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequiredArgsConstructor
public class FrequentlyAskedQuestionApiController implements FrequentlyAskedQuestionApi {

    private final FrequentlyAskedQuestionSearchService searchService;

    @Operation(
            tags = {"frequently_asked_question",},
            summary = "FAQ登録API",
            description = "FAQを1件登録する",
            operationId = "frequentlyAskedQuestionCreate",
            responses = {
                    @ApiResponse(responseCode = "200", description = "正常系", content = @Content(schema = @Schema(implementation = PostObject.class))),
                    @ApiResponse(responseCode = "400", description = "バリデーションエラー", content = @Content(schema = @Schema(implementation = ValidationResult.class))),
            }
    )
    @Override
    public CompletableFuture<ResponseEntity<PostObject>> frequentlyAskedQuestionCreate(String cats, @Valid FrequentlyAskedQuestionCreate frequentlyAskedQuestionCreate) {
        return null;
    }

    @Operation(
            tags = {"frequently_asked_question",},
            summary = "FAQ削除API",
            description = "FAQを1件論理削除する",
            operationId = "frequentlyAskedQuestionDelete",
            responses = {
                    @ApiResponse(responseCode = "204", description = "正常系"),
                    @ApiResponse(responseCode = "404", description = "Idが見つからない"),
                    @ApiResponse(responseCode = "409", description = "排他失敗"),
            }
    )
    @Override
    public CompletableFuture<ResponseEntity<Void>> frequentlyAskedQuestionDelete(String cats, Integer faqId, @NotNull @Valid Integer version) {
        return null;
    }

    @Operation(
            tags = {"frequently_asked_question",},
            summary = "FAQ詳細取得API",
            description = "FAQ詳細を1件取得する",
            operationId = "frequentlyAskedQuestionFind",
            responses = {
                    @ApiResponse(responseCode = "200", description = "正常系", content = @Content(schema = @Schema(implementation = FrequentlyAskedQuestionDetail.class))),
                    @ApiResponse(responseCode = "400", description = "バリデーションエラー", content = @Content(schema = @Schema(implementation = ValidationResult.class))),
                    @ApiResponse(responseCode = "404", description = "Idが見つからない"),
            }
    )
    @Override
    public CompletableFuture<ResponseEntity<FrequentlyAskedQuestionDetail>> frequentlyAskedQuestionFind(String cats, Integer faqId) {
        return null;
    }

    @Operation(
            tags = {"frequently_asked_question",},
            summary = "FAQ一覧取得API",
            description = "FAQを取得する",
            operationId = "frequentlyAskedQuestionSearch",
            responses = {
                    @ApiResponse(responseCode = "200", description = "正常系", content = @Content(array = @ArraySchema(schema = @Schema(implementation = FrequentlyAskedQuestionDetail.class)))),
                    @ApiResponse(responseCode = "400", description = "バリデーションエラー", content = @Content(schema = @Schema(implementation = ValidationResult.class))),
            }
    )
    @Override
    public CompletableFuture<ResponseEntity<List<FrequentlyAskedQuestionDetail>>> frequentlyAskedQuestionSearch(String cats, @Valid List<Integer> storeIds) {
        return CompletableFuture.completedFuture(new ResponseEntity<>(searchService.search(), HttpStatus.OK));
    }

    @Operation(
            tags = {"frequently_asked_question",},
            summary = "FAQ更新API",
            description = "FAQを1件更新する",
            operationId = "frequentlyAskedQuestionUpdate",
            responses = {
                    @ApiResponse(responseCode = "200", description = "正常系", content = @Content(schema = @Schema(implementation = PatchObject.class))),
                    @ApiResponse(responseCode = "400", description = "バリデーションエラー", content = @Content(schema = @Schema(implementation = ValidationResult.class))),
                    @ApiResponse(responseCode = "404", description = "Idが見つからない"),
                    @ApiResponse(responseCode = "409", description = "排他失敗"),
            }
    )
    @Override
    public CompletableFuture<ResponseEntity<PatchObject>> frequentlyAskedQuestionUpdate(String cats, Integer faqId, @Valid FrequentlyAskedQuestionUpdate frequentlyAskedQuestionUpdate) {
        return null;
    }

}
