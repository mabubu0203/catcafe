package mabubu0203.com.github.catcafe.api.controller.provide.service;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import mabubu0203.com.github.catcafe.api.controller.provide.service.mapper.request.ProvideServiceSearchRequestMapper;
import mabubu0203.com.github.catcafe.api.controller.provide.service.mapper.response.ProvideServiceSearchResponseMapper;
import mabubu0203.com.github.catcafe.api.service.provide.service.ProvideServiceSearchService;
import org.openapitools.api.ProvideServiceApi;
import org.openapitools.model.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequiredArgsConstructor
public class ProvideServiceApiController implements ProvideServiceApi {

    private final ProvideServiceSearchService searchService;

    @Operation(
            tags = {"provide_service",},
            summary = "提供サービス登録API",
            description = "提供サービスを1件登録する",
            operationId = "provideServiceCreate",
            responses = {
                    @ApiResponse(responseCode = "200", description = "正常系", content = @Content(schema = @Schema(implementation = PostObject.class))),
                    @ApiResponse(responseCode = "400", description = "バリデーションエラー", content = @Content(schema = @Schema(implementation = ValidationResult.class))),
            }
    )
    @Override
    public CompletableFuture<ResponseEntity<PostObject>> provideServiceCreate(String cats, Integer storeId, @Valid ProvideServiceCreate provideServiceCreate) {
        return null;
    }

    @Operation(
            tags = {"provide_service",},
            summary = "提供サービス削除API",
            description = "提供サービスを1件論理削除する",
            operationId = "provideServiceDelete",
            responses = {
                    @ApiResponse(responseCode = "204", description = "正常系"),
                    @ApiResponse(responseCode = "404", description = "Idが見つからない"),
                    @ApiResponse(responseCode = "409", description = "排他失敗"),
            }
    )
    @Override
    public CompletableFuture<ResponseEntity<Void>> provideServiceDelete(String cats, Integer storeId, Integer provideServiceId, @NotNull @Valid Integer version) {
        return null;
    }

    @Operation(
            tags = {"provide_service",},
            summary = "提供サービス詳細取得API",
            description = "提供サービス詳細を1件取得する",
            operationId = "provideServiceFind",
            responses = {
                    @ApiResponse(responseCode = "200", description = "正常系", content = @Content(schema = @Schema(implementation = ProvideServiceFindResponse.class))),
                    @ApiResponse(responseCode = "400", description = "バリデーションエラー", content = @Content(schema = @Schema(implementation = ValidationResult.class))),
                    @ApiResponse(responseCode = "404", description = "Idが見つからない"),
            }
    )
    @Override
    public CompletableFuture<ResponseEntity<ProvideServiceFindResponse>> provideServiceFind(String cats, Integer storeId, Integer provideServiceId) {
        return null;
    }

    @Operation(
            tags = {"provide_service",},
            summary = "提供サービス一覧取得API",
            description = "提供サービスを取得する",
            operationId = "provideServiceSearch",
            responses = {
                    @ApiResponse(responseCode = "200", description = "正常系", content = @Content(schema = @Schema(implementation = ProvideServiceSearchResponse.class))),
                    @ApiResponse(responseCode = "400", description = "バリデーションエラー", content = @Content(schema = @Schema(implementation = ValidationResult.class))),
            }
    )
    @CrossOrigin
    @Override
    public CompletableFuture<ResponseEntity<ProvideServiceSearchResponse>> provideServiceSearch(String cats, @Valid List<Integer> storeIds) {
        return new ProvideServiceSearchRequestMapper(cats, storeIds)
                .get()
                .map(this.searchService::promise)
                .map(result -> result.thenApply(new ProvideServiceSearchResponseMapper().andThen(ResponseEntity.status(HttpStatus.OK)::body)))
                .orElseGet(() -> CompletableFuture.completedFuture(new ResponseEntity<>(null, HttpStatus.BAD_REQUEST)));
    }

    @Operation(
            tags = {"provide_service",},
            summary = "提供サービス更新API",
            description = "提供サービスを1件更新する",
            operationId = "provideServiceUpdate",
            responses = {
                    @ApiResponse(responseCode = "200", description = "正常系", content = @Content(schema = @Schema(implementation = PatchObject.class))),
                    @ApiResponse(responseCode = "400", description = "バリデーションエラー", content = @Content(schema = @Schema(implementation = ValidationResult.class))),
                    @ApiResponse(responseCode = "404", description = "Idが見つからない"),
                    @ApiResponse(responseCode = "409", description = "排他失敗"),
            }
    )
    @Override
    public CompletableFuture<ResponseEntity<PatchObject>> provideServiceUpdate(String cats, Integer storeId, Integer provideServiceId, @Valid ProvideServiceUpdate provideServiceUpdate) {
        return null;
    }

}
