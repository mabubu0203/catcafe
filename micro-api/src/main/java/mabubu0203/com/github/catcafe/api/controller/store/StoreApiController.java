package mabubu0203.com.github.catcafe.api.controller.store;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import mabubu0203.com.github.catcafe.api.controller.store.mapper.request.StoreCreateRequestMapper;
import mabubu0203.com.github.catcafe.api.controller.store.mapper.request.StoreSearchRequestMapper;
import mabubu0203.com.github.catcafe.api.controller.store.mapper.response.StoreCreateResponseMapper;
import mabubu0203.com.github.catcafe.api.controller.store.mapper.response.StoreSearchResponseMapper;
import mabubu0203.com.github.catcafe.api.service.store.StoreRegisterService;
import mabubu0203.com.github.catcafe.api.service.store.StoreSearchService;
import org.openapitools.api.StoreApi;
import org.openapitools.model.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@RestController
@RequiredArgsConstructor
public class StoreApiController implements StoreApi {

    private final StoreRegisterService registerService;
    private final StoreSearchService searchService;

    @Operation(
            tags = {"store",},
            summary = "店舗登録API",
            description = "店舗を1件登録する",
            operationId = "storeCreate",
            responses = {
                    @ApiResponse(responseCode = "200", description = "正常系", content = @Content(schema = @Schema(implementation = PostObject.class))),
                    @ApiResponse(responseCode = "400", description = "バリデーションエラー", content = @Content(schema = @Schema(implementation = ValidationResult.class))),
            }
    )
    @Override
    public CompletableFuture<ResponseEntity<PostObject>> storeCreate(
            @Parameter(description = "カフェ識別子", schema = @Schema(allowableValues = {"cats"})) String cats,
            @Valid StoreCreate storeCreate) {
        return Optional.of(storeCreate)
                .map(new StoreCreateRequestMapper(cats))
                .map(this.registerService::promise)
                .map(result -> result.thenApply(new StoreCreateResponseMapper().andThen(ResponseEntity.status(HttpStatus.OK)::body)))
                .orElseGet(() -> CompletableFuture.completedFuture(new ResponseEntity<>(null, HttpStatus.BAD_REQUEST)));
    }

    @Operation(
            tags = {"store",},
            summary = "店舗削除API",
            description = "店舗を1件論理削除する",
            operationId = "storeDelete",
            responses = {
                    @ApiResponse(responseCode = "204", description = "正常系"),
                    @ApiResponse(responseCode = "404", description = "Idが見つからない"),
                    @ApiResponse(responseCode = "409", description = "排他失敗"),
            }
    )
    @Override
    public CompletableFuture<ResponseEntity<Void>> storeDelete(String cats, Integer storeId, @NotNull @Valid Integer version) {
        return null;
    }

    @Operation(
            tags = {"store",},
            summary = "店舗詳細取得API",
            description = "店舗詳細を1件取得する",
            operationId = "storeFind",
            responses = {
                    @ApiResponse(responseCode = "200", description = "正常系", content = @Content(schema = @Schema(implementation = StoreFindResponse.class))),
                    @ApiResponse(responseCode = "400", description = "バリデーションエラー", content = @Content(schema = @Schema(implementation = ValidationResult.class))),
                    @ApiResponse(responseCode = "404", description = "Idが見つからない"),
            }
    )
    @Override
    public CompletableFuture<ResponseEntity<StoreFindResponse>> storeFind(
            @Parameter(description = "カフェ識別子", schema = @Schema(allowableValues = {"cats"})) String cats,
            @Parameter(description = "店舗ID", schema = @Schema(type = "integer")) Integer storeId) {
        return null;
    }

    @Operation(
            tags = {"store",},
            summary = "店舗一覧取得API",
            description = "店舗を取得する",
            operationId = "storeSearch",
            responses = {
                    @ApiResponse(responseCode = "200", description = "正常系", content = @Content(schema = @Schema(implementation = StoreSearchResponse.class))),
                    @ApiResponse(responseCode = "400", description = "バリデーションエラー", content = @Content(schema = @Schema(implementation = ValidationResult.class))),
            }
    )
    @Override
    public CompletableFuture<ResponseEntity<StoreSearchResponse>> storeSearch(
            @Parameter(description = "カフェ識別子", schema = @Schema(allowableValues = {"cats"})) String cats,
            @Parameter(description = "店舗ID") @Valid List<Integer> storeIds,
            @Parameter(description = "取得ページ", schema = @Schema(type = "integer", maxProperties = 100)) @Valid @Min(0) @Max(100) Integer page,
            @Parameter(description = "取得サイズ", schema = @Schema(type = "integer", minProperties = 1, maxProperties = 20)) @Valid @Min(1) @Max(20) Integer size,
            @Parameter(description = "ソートキー", array = @ArraySchema(schema = @Schema(allowableValues = {"store_id.asc", "store_id.desc"}))) @Valid List<String> sortKeys) {
        return new StoreSearchRequestMapper(
                cats, storeIds,
                page, size, sortKeys
        ).get()
                .map(this.searchService::promise)
                .map(result -> result.thenApply(new StoreSearchResponseMapper().andThen(ResponseEntity.status(HttpStatus.OK)::body)))
                .orElseGet(() -> CompletableFuture.completedFuture(new ResponseEntity<>(null, HttpStatus.BAD_REQUEST)));
    }

    @Operation(
            tags = {"store",},
            summary = "店舗更新API",
            description = "店舗を1件更新する",
            operationId = "storeUpdate",
            responses = {
                    @ApiResponse(responseCode = "200", description = "正常系", content = @Content(schema = @Schema(implementation = PatchObject.class))),
                    @ApiResponse(responseCode = "400", description = "バリデーションエラー", content = @Content(schema = @Schema(implementation = ValidationResult.class))),
                    @ApiResponse(responseCode = "404", description = "Idが見つからない"),
                    @ApiResponse(responseCode = "409", description = "排他失敗"),
            }
    )
    @Override
    public CompletableFuture<ResponseEntity<PatchObject>> storeUpdate(String cats, Integer storeId, @Valid StoreUpdate storeUpdate) {
        return null;
    }

}
