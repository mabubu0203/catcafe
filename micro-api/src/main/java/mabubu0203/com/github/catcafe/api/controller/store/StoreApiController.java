package mabubu0203.com.github.catcafe.api.controller.store;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import mabubu0203.com.github.catcafe.api.controller.store.helper.request.StoreCreateRequestMapper;
import mabubu0203.com.github.catcafe.api.controller.store.helper.request.StoreSearchRequestMapper;
import mabubu0203.com.github.catcafe.api.controller.store.helper.response.StoreCreateResponseMapper;
import mabubu0203.com.github.catcafe.api.controller.store.helper.response.StoreSearchResponseMapper;
import mabubu0203.com.github.catcafe.api.controller.store.service.StoreRegisterService;
import mabubu0203.com.github.catcafe.api.controller.store.service.StoreSearchService;
import org.openapitools.api.StoreApi;
import org.openapitools.model.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

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
    public Mono<ResponseEntity<PostObject>> storeCreate(
            @Parameter(description = "カフェ識別子", schema = @Schema(allowableValues = {"cats"})) String cats,
            @Valid Mono<StoreCreate> storeCreate,
            ServerWebExchange exchange) {
        return storeCreate
                .map(new StoreCreateRequestMapper(cats))
                .map(this.registerService::promise)
                .flatMap(Mono::fromCompletionStage)
                .map(new StoreCreateResponseMapper())
                .map(ResponseEntity.status(HttpStatus.OK)::body);
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
    public Mono<ResponseEntity<Void>> storeDelete(
            @Parameter(description = "カフェ識別子", schema = @Schema(allowableValues = {"cats"})) String cats,
            Integer storeId,
            @NotNull @Valid Integer version,
            ServerWebExchange exchange) {
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
    public Mono<ResponseEntity<StoreFindResponse>> storeFind(
            @Parameter(description = "カフェ識別子", schema = @Schema(allowableValues = {"cats"})) String cats,
            @Parameter(description = "店舗ID", schema = @Schema(type = "integer")) Integer storeId,
            ServerWebExchange exchange) {
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
    @CrossOrigin
    @Override
    public Mono<ResponseEntity<StoreSearchResponse>> storeSearch(
            @Parameter(description = "カフェ識別子", schema = @Schema(allowableValues = {"cats"})) String cats,
            @Parameter(description = "店舗ID") @Valid List<Integer> storeIds,
            @Parameter(description = "取得ページ", schema = @Schema(type = "integer", maxProperties = 100)) @Valid @Min(0) @Max(100) Integer page,
            @Parameter(description = "取得サイズ", schema = @Schema(type = "integer", minProperties = 1, maxProperties = 20)) @Valid @Min(1) @Max(20) Integer size,
            @Parameter(description = "ソートキー", array = @ArraySchema(schema = @Schema(allowableValues = {"store_id.asc", "store_id.desc"}))) @Valid List<String> sortKeys,
            ServerWebExchange exchange) {
        return new StoreSearchRequestMapper(
                cats, storeIds,
                page, size, sortKeys).get()
                .map(this.searchService::promise)
                .flatMap(Mono::fromCompletionStage)
                .map(new StoreSearchResponseMapper())
                .map(ResponseEntity.status(HttpStatus.OK)::body);
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
    public Mono<ResponseEntity<PatchObject>> storeUpdate(
            @Parameter(description = "カフェ識別子", schema = @Schema(allowableValues = {"cats"})) String cats,
            Integer storeId,
            @Valid Mono<StoreUpdate> storeUpdate,
            ServerWebExchange exchange) {
        return null;
    }

}
