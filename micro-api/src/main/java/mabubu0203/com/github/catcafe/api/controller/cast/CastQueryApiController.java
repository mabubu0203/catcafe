package mabubu0203.com.github.catcafe.api.controller.cast;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import mabubu0203.com.github.catcafe.api.controller.cast.helper.request.CastFindRequestMapper;
import mabubu0203.com.github.catcafe.api.controller.cast.helper.request.CastSearchRequestMapper;
import mabubu0203.com.github.catcafe.api.controller.cast.helper.response.CastFindResponseMapper;
import mabubu0203.com.github.catcafe.api.controller.cast.helper.response.CastSearchResponseMapper;
import mabubu0203.com.github.catcafe.api.controller.cast.service.CastSearchService;
import org.openapitools.api.CastQueryApi;
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
import java.util.List;

@RestController
@RequiredArgsConstructor
public class CastQueryApiController implements CastQueryApi {

    private final CastSearchService castSearchService;

    @Operation(
            tags = {"cast_query",},
            summary = "キャスト(猫)詳細取得API",
            description = "キャスト(猫)詳細を1件取得する",
            operationId = "castCatFind",
            responses = {
                    @ApiResponse(responseCode = "200", description = "正常系", content = @Content(schema = @Schema(implementation = CastCatFindResponse.class))),
                    @ApiResponse(responseCode = "400", description = "バリデーションエラー", content = @Content(schema = @Schema(implementation = ValidationResult.class))),
                    @ApiResponse(responseCode = "404", description = "Idが見つからない"),
            }
    )
    @Override
    public Mono<ResponseEntity<CastCatFindResponse>> castCatFind(
            @Parameter(description = "カフェ識別子", schema = @Schema(allowableValues = {"cats"})) String cats,
            Integer castCatId,
            ServerWebExchange exchange) {
        return null;
    }

    @Operation(
            tags = {"cast_query",},
            summary = "キャスト(猫)一覧取得API",
            description = "キャスト(猫)を取得する",
            operationId = "castCatSearch",
            responses = {
                    @ApiResponse(responseCode = "200", description = "正常系", content = @Content(schema = @Schema(implementation = CastCatSearchResponse.class))),
                    @ApiResponse(responseCode = "400", description = "バリデーションエラー", content = @Content(schema = @Schema(implementation = ValidationResult.class))),
            }
    )
    @Override
    public Mono<ResponseEntity<CastCatSearchResponse>> castCatSearch(
            @Parameter(description = "カフェ識別子", schema = @Schema(allowableValues = {"cats"})) String cats,
            @Valid Integer size,
            ServerWebExchange exchange) {
        return null;
    }


    @Operation(
            tags = {"cast_query",},
            summary = "キャスト詳細取得API",
            description = "キャスト詳細を1件取得する",
            operationId = "castFind",
            responses = {
                    @ApiResponse(responseCode = "200", description = "正常系", content = @Content(schema = @Schema(implementation = CastFindResponse.class))),
                    @ApiResponse(responseCode = "400", description = "バリデーションエラー", content = @Content(schema = @Schema(implementation = ValidationResult.class))),
                    @ApiResponse(responseCode = "404", description = "Idが見つからない"),
            }
    )
    @CrossOrigin
    @Override
    public Mono<ResponseEntity<CastFindResponse>> castFind(
            @Parameter(description = "カフェ識別子", schema = @Schema(allowableValues = {"cats"})) String cats,
            @Parameter(description = "店舗ID", schema = @Schema(type = "integer")) Integer storeId,
            @Parameter(description = "キャストID", schema = @Schema(type = "integer")) Integer castId,
            ServerWebExchange exchange) {
        return new CastFindRequestMapper(
                cats, storeId, castId).get()
                .map(this.castSearchService::promise)
                .flatMap(Mono::fromCompletionStage)
                .map(new CastFindResponseMapper())
                .map(ResponseEntity.status(HttpStatus.OK)::body);
    }


    @Operation(
            tags = {"cast_query",},
            summary = "キャスト一覧取得API",
            description = "キャストを取得する",
            operationId = "castSearch",
            responses = {
                    @ApiResponse(responseCode = "200", description = "正常系", content = @Content(schema = @Schema(implementation = CastSearchResponse.class))),
                    @ApiResponse(responseCode = "400", description = "バリデーションエラー", content = @Content(schema = @Schema(implementation = ValidationResult.class))),
            }
    )
    @CrossOrigin
    @Override
    public Mono<ResponseEntity<CastSearchResponse>> castSearch(
            @Parameter(description = "カフェ識別子", schema = @Schema(allowableValues = {"cats"})) String cats,
            @Parameter(description = "店舗ID") @Valid List<Integer> storeIds,
            @Parameter(description = "キャストID") @Valid List<Integer> castIds,
            @Parameter(description = "取得ページ", schema = @Schema(type = "integer", maxProperties = 100)) @Valid @Min(0) @Max(100) Integer page,
            @Parameter(description = "取得サイズ", schema = @Schema(type = "integer", minProperties = 1, maxProperties = 20)) @Valid @Min(1) @Max(20) Integer size,
            @Parameter(description = "ソートキー", array = @ArraySchema(schema = @Schema(allowableValues = {"store_id.asc", "store_id.desc"}))) @Valid List<String> sortKeys,
            ServerWebExchange exchange) {
        return new CastSearchRequestMapper(
                cats, storeIds, castIds,
                page, size, sortKeys).get()
                .map(this.castSearchService::promise)
                .flatMap(Mono::fromCompletionStage)
                .map(new CastSearchResponseMapper())
                .map(ResponseEntity.status(HttpStatus.OK)::body);
    }

}