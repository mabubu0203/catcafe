package mabubu0203.com.github.catcafe.api.controller.frequently.asked.question;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import mabubu0203.com.github.catcafe.api.controller.frequently.asked.question.helper.request.FrequentlyAskedQuestionFindRequestMapper;
import mabubu0203.com.github.catcafe.api.controller.frequently.asked.question.helper.request.FrequentlyAskedQuestionSearchRequestMapper;
import mabubu0203.com.github.catcafe.api.controller.frequently.asked.question.helper.response.FrequentlyAskedQuestionFindResponseMapper;
import mabubu0203.com.github.catcafe.api.controller.frequently.asked.question.helper.response.FrequentlyAskedQuestionSearchResponseMapper;
import mabubu0203.com.github.catcafe.api.controller.frequently.asked.question.service.FrequentlyAskedQuestionSearchService;
import org.openapitools.api.FrequentlyAskedQuestionApi;
import org.openapitools.model.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

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
    public Mono<ResponseEntity<PostObject>> frequentlyAskedQuestionCreate(
            @Parameter(description = "カフェ識別子", schema = @Schema(allowableValues = {"cats"})) String cats,
            @Valid Mono<FrequentlyAskedQuestionCreate> frequentlyAskedQuestionCreate,
            ServerWebExchange exchange) {
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
    public Mono<ResponseEntity<Void>> frequentlyAskedQuestionDelete(
            @Parameter(description = "カフェ識別子", schema = @Schema(allowableValues = {"cats"})) String cats,
            Integer faqId,
            @NotNull @Valid Integer version,
            ServerWebExchange exchange) {
        return null;
    }

    @Operation(
            tags = {"frequently_asked_question",},
            summary = "FAQ詳細取得API",
            description = "FAQ詳細を1件取得する",
            operationId = "frequentlyAskedQuestionFind",
            responses = {
                    @ApiResponse(responseCode = "200", description = "正常系", content = @Content(schema = @Schema(implementation = FrequentlyAskedQuestionFindResponse.class))),
                    @ApiResponse(responseCode = "400", description = "バリデーションエラー", content = @Content(schema = @Schema(implementation = ValidationResult.class))),
                    @ApiResponse(responseCode = "404", description = "Idが見つからない"),
            }
    )
    @Override
    public Mono<ResponseEntity<FrequentlyAskedQuestionFindResponse>> frequentlyAskedQuestionFind(
            @Parameter(description = "カフェ識別子", schema = @Schema(allowableValues = {"cats"})) String cats,
            Integer faqId,
            ServerWebExchange exchange) {
        return new FrequentlyAskedQuestionFindRequestMapper(cats, faqId).get()
                .map(this.searchService::promise)
                .flatMap(Mono::fromCompletionStage)
                .map(new FrequentlyAskedQuestionFindResponseMapper())
                .map(ResponseEntity.status(HttpStatus.OK)::body);
    }

    @Operation(
            tags = {"frequently_asked_question",},
            summary = "FAQ一覧取得API",
            description = "FAQを取得する",
            operationId = "frequentlyAskedQuestionSearch",
            responses = {
                    @ApiResponse(responseCode = "200", description = "正常系", content = @Content(schema = @Schema(implementation = FrequentlyAskedQuestionSearchResponse.class))),
                    @ApiResponse(responseCode = "400", description = "バリデーションエラー", content = @Content(schema = @Schema(implementation = ValidationResult.class))),
            }
    )
    @CrossOrigin
    @Override
    public Mono<ResponseEntity<FrequentlyAskedQuestionSearchResponse>> frequentlyAskedQuestionSearch(
            @Parameter(description = "カフェ識別子", schema = @Schema(allowableValues = {"cats"})) String cats,
            @Valid List<Integer> storeIds,
            ServerWebExchange exchange) {
        return new FrequentlyAskedQuestionSearchRequestMapper(
                cats, storeIds).get()
                .map(this.searchService::promise)
                .flatMap(Mono::fromCompletionStage)
                .map(new FrequentlyAskedQuestionSearchResponseMapper())
                .map(ResponseEntity.status(HttpStatus.OK)::body);
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
    public Mono<ResponseEntity<PatchObject>> frequentlyAskedQuestionUpdate(
            @Parameter(description = "カフェ識別子", schema = @Schema(allowableValues = {"cats"})) String cats,
            Integer faqId,
            @Valid Mono<FrequentlyAskedQuestionUpdate> frequentlyAskedQuestionUpdate,
            ServerWebExchange exchange) {
        return null;
    }

}
