package mabubu0203.com.github.catcafe.api.controller.notice;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import mabubu0203.com.github.catcafe.api.controller.notice.helper.request.NoticeSearchRequestMapper;
import mabubu0203.com.github.catcafe.api.controller.notice.helper.response.NoticeSearchResponseMapper;
import mabubu0203.com.github.catcafe.api.controller.notice.service.NoticeSearchService;
import org.openapitools.api.NoticeApi;
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
import java.util.concurrent.CompletableFuture;

@RestController
@RequiredArgsConstructor
public class NoticeApiController implements NoticeApi {

    private final NoticeSearchService searchService;

    @Operation(
            tags = {"notice",},
            summary = "お知らせ登録API",
            description = "お知らせを1件登録する",
            operationId = "noticeCreate",
            responses = {
                    @ApiResponse(responseCode = "200", description = "正常系", content = @Content(schema = @Schema(implementation = PostObject.class))),
                    @ApiResponse(responseCode = "400", description = "バリデーションエラー", content = @Content(schema = @Schema(implementation = ValidationResult.class))),
            }
    )
    @Override
    public Mono<ResponseEntity<PostObject>> noticeCreate(
            String cats,
            @Valid Mono<NoticeCreate> noticeCreate,
            ServerWebExchange exchange) {
        return null;
    }

    @Operation(
            tags = {"notice",},
            summary = "お知らせ削除API",
            description = "お知らせを1件論理削除する",
            operationId = "noticeDelete",
            responses = {
                    @ApiResponse(responseCode = "204", description = "正常系"),
                    @ApiResponse(responseCode = "404", description = "Idが見つからない"),
                    @ApiResponse(responseCode = "409", description = "排他失敗"),
            }
    )
    @Override
    public Mono<ResponseEntity<Void>> noticeDelete(
            String cats,
            Integer noticeId,
            @NotNull @Valid Integer version,
            ServerWebExchange exchange) {
        return null;
    }

    @Operation(
            tags = {"notice",},
            summary = "お知らせ詳細取得API",
            description = "お知らせを1件取得する",
            operationId = "noticeFind",
            responses = {
                    @ApiResponse(responseCode = "200", description = "正常系", content = @Content(schema = @Schema(implementation = NoticeFindResponse.class))),
                    @ApiResponse(responseCode = "400", description = "バリデーションエラー", content = @Content(schema = @Schema(implementation = ValidationResult.class))),
                    @ApiResponse(responseCode = "404", description = "Idが見つからない"),
            }
    )
    @Override
    public Mono<ResponseEntity<NoticeFindResponse>> noticeFind(
            String cats,
            Integer noticeId,
            ServerWebExchange exchange) {
        return null;
    }

    @Operation(
            tags = {"notice",},
            summary = "お知らせ一覧取得API",
            description = "お知らせを取得する",
            operationId = "noticeSearch",
            responses = {
                    @ApiResponse(responseCode = "200", description = "正常系", content = @Content(schema = @Schema(implementation = NoticeSearchResponse.class))),
                    @ApiResponse(responseCode = "400", description = "バリデーションエラー", content = @Content(schema = @Schema(implementation = ValidationResult.class))),
            }
    )
    @CrossOrigin
    @Override
    public Mono<ResponseEntity<NoticeSearchResponse>> noticeSearch(
            String cats,
            @Valid List<Integer> storeIds,
            @Valid Integer size,
            ServerWebExchange exchange) {
        return
                Mono.fromCompletionStage(
                        new NoticeSearchRequestMapper(cats, storeIds)
                                .get()
                                .map(this.searchService::promise)
                                .map(result -> result.thenApply(new NoticeSearchResponseMapper().andThen(ResponseEntity.status(HttpStatus.OK)::body)))
                                .orElseGet(() -> CompletableFuture.completedFuture(new ResponseEntity<>(null, HttpStatus.BAD_REQUEST)))
                );
    }

    @Operation(
            tags = {"notice",},
            summary = "お知らせ更新API",
            description = "お知らせを1件更新する",
            operationId = "noticeUpdate",
            responses = {
                    @ApiResponse(responseCode = "200", description = "正常系", content = @Content(schema = @Schema(implementation = PatchObject.class))),
                    @ApiResponse(responseCode = "400", description = "バリデーションエラー", content = @Content(schema = @Schema(implementation = ValidationResult.class))),
                    @ApiResponse(responseCode = "404", description = "Idが見つからない"),
                    @ApiResponse(responseCode = "409", description = "排他失敗"),
            }
    )
    @Override
    public Mono<ResponseEntity<PatchObject>> noticeUpdate(
            String cats,
            Integer noticeId,
            @Valid Mono<NoticeUpdate> noticeUpdate,
            ServerWebExchange exchange) {
        return null;
    }

}
