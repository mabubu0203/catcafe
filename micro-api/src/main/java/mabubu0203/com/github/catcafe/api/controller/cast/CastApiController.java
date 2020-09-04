package mabubu0203.com.github.catcafe.api.controller.cast;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import mabubu0203.com.github.catcafe.api.controller.cast.mapper.request.CastFindRequestMapper;
import mabubu0203.com.github.catcafe.api.controller.cast.mapper.request.CastSearchRequestMapper;
import mabubu0203.com.github.catcafe.api.controller.cast.mapper.response.CastFindResponseMapper;
import mabubu0203.com.github.catcafe.api.controller.cast.mapper.response.CastSearchResponseMapper;
import mabubu0203.com.github.catcafe.api.service.cast.CastSearchService;
import org.openapitools.api.CastApi;
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
public class CastApiController implements CastApi {

    private final CastSearchService searchService;

    @Operation(
            tags = {"cast",},
            summary = "キャスト登録API",
            description = "キャストを1件登録する",
            operationId = "castCreate",
            responses = {
                    @ApiResponse(responseCode = "200", description = "正常系", content = @Content(schema = @Schema(implementation = PostObject.class))),
                    @ApiResponse(responseCode = "400", description = "バリデーションエラー", content = @Content(schema = @Schema(implementation = ValidationResult.class))),
            }
    )
    @Override
    public CompletableFuture<ResponseEntity<PostObject>> castCreate(String cats, Integer storeId, @Valid CastCreate castCreate) {
        return null;
    }

    @Operation(
            tags = {"cast",},
            summary = "キャスト削除API",
            description = "キャストを1件論理削除する",
            operationId = "castDelete",
            responses = {
                    @ApiResponse(responseCode = "204", description = "正常系"),
                    @ApiResponse(responseCode = "404", description = "Idが見つからない"),
                    @ApiResponse(responseCode = "409", description = "排他失敗"),
            }
    )
    @Override
    public CompletableFuture<ResponseEntity<Void>> castDelete(String cats, Integer storeId, Integer castId, @NotNull @Valid Integer version) {
        return null;
    }

    @Operation(
            tags = {"cast",},
            summary = "キャスト詳細取得API",
            description = "キャスト詳細を1件取得する",
            operationId = "castFind",
            responses = {
                    @ApiResponse(responseCode = "200", description = "正常系", content = @Content(schema = @Schema(implementation = CastFindResponse.class))),
                    @ApiResponse(responseCode = "400", description = "バリデーションエラー", content = @Content(schema = @Schema(implementation = ValidationResult.class))),
                    @ApiResponse(responseCode = "404", description = "Idが見つからない"),
            }
    )
    @Override
    public CompletableFuture<ResponseEntity<CastFindResponse>> castFind(String cats, Integer storeId, Integer castId) {
        return new CastFindRequestMapper(cats, storeId, castId)
                .get()
                .map(this.searchService::promise)
                .map(result -> result.thenApply(new CastFindResponseMapper().andThen(ResponseEntity.status(HttpStatus.OK)::body)))
                .orElseGet(() -> CompletableFuture.completedFuture(new ResponseEntity<>(null, HttpStatus.BAD_REQUEST)));
    }

    @Operation(
            tags = {"cast",},
            summary = "キャスト一覧取得API",
            description = "キャストを取得する",
            operationId = "castSearch",
            responses = {
                    @ApiResponse(responseCode = "200", description = "正常系", content = @Content(schema = @Schema(implementation = CastSearchResponse.class))),
                    @ApiResponse(responseCode = "400", description = "バリデーションエラー", content = @Content(schema = @Schema(implementation = ValidationResult.class))),
            }
    )
    @Override
    public CompletableFuture<ResponseEntity<CastSearchResponse>> castSearch(String cats, @Valid List<Integer> storeIds, Integer size) {
        return new CastSearchRequestMapper(cats, storeIds)
                .get()
                .map(this.searchService::promise)
                .map(result -> result.thenApply(new CastSearchResponseMapper().andThen(ResponseEntity.status(HttpStatus.OK)::body)))
                .orElseGet(() -> CompletableFuture.completedFuture(new ResponseEntity<>(null, HttpStatus.BAD_REQUEST)));
    }

    @Operation(
            tags = {"cast",},
            summary = "キャスト更新API",
            description = "キャストを1件更新する",
            operationId = "castUpdate",
            responses = {
                    @ApiResponse(responseCode = "200", description = "正常系", content = @Content(schema = @Schema(implementation = PatchObject.class))),
                    @ApiResponse(responseCode = "400", description = "バリデーションエラー", content = @Content(schema = @Schema(implementation = ValidationResult.class))),
                    @ApiResponse(responseCode = "404", description = "Idが見つからない"),
                    @ApiResponse(responseCode = "409", description = "排他失敗"),
            }
    )
    @Override
    public CompletableFuture<ResponseEntity<PatchObject>> castUpdate(String cats, Integer storeId, Integer castId, @Valid CastUpdate castUpdate) {
        return null;
    }

}
