package mabubu0203.com.github.catcafe.api.controller.cast;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import mabubu0203.com.github.catcafe.api.controller.cast.helper.request.CastCatCreateRequestMapper;
import mabubu0203.com.github.catcafe.api.controller.cast.helper.request.CastCreateRequestMapper;
import mabubu0203.com.github.catcafe.api.controller.cast.helper.response.CastCatCreateResponseMapper;
import mabubu0203.com.github.catcafe.api.controller.cast.helper.response.CastCreateResponseMapper;
import mabubu0203.com.github.catcafe.api.controller.cast.service.CastCatResisterService;
import mabubu0203.com.github.catcafe.api.controller.cast.service.CastRegisterService;
import org.openapitools.api.CastCommandApi;
import org.openapitools.model.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequiredArgsConstructor
public class CastCommandApiController implements CastCommandApi {

    private final CastCatResisterService castCatResisterService;
    private final CastRegisterService castResisterService;

    @Operation(
            tags = {"cast_command",},
            summary = "キャスト(猫)登録API",
            description = "キャスト(猫)を1件登録する",
            operationId = "castCatCreate",
            responses = {
                    @ApiResponse(responseCode = "200", description = "正常系", content = @Content(schema = @Schema(implementation = PostObject.class))),
                    @ApiResponse(responseCode = "400", description = "バリデーションエラー", content = @Content(schema = @Schema(implementation = ValidationResult.class))),
            }
    )
    @Override
    public Mono<ResponseEntity<PostObject>> castCatCreate(
            @Parameter(description = "カフェ識別子", schema = @Schema(allowableValues = {"cats"})) String cats,
            @Valid Mono<CastCatCreate> castCatCreate,
            ServerWebExchange exchange) {
        return castCatCreate
                .map(new CastCatCreateRequestMapper(cats))
                .map(this.castCatResisterService::promise)
                .flatMap(Mono::fromCompletionStage)
                .map(new CastCatCreateResponseMapper())
                .map(ResponseEntity.status(HttpStatus.OK)::body);
    }

    @Operation(
            tags = {"cast_command",},
            summary = "キャスト(猫)削除API",
            description = "キャスト(猫)を1件論理削除する",
            operationId = "castCatDelete",
            responses = {
                    @ApiResponse(responseCode = "204", description = "正常系"),
                    @ApiResponse(responseCode = "404", description = "Idが見つからない"),
                    @ApiResponse(responseCode = "409", description = "排他失敗"),
            }
    )
    @Override
    public Mono<ResponseEntity<Void>> castCatDelete(
            @Parameter(description = "カフェ識別子", schema = @Schema(allowableValues = {"cats"})) String cats,
            Integer castCatId,
            @NotNull @Valid Integer version,
            ServerWebExchange exchange) {
        return null;
    }

    @Operation(
            tags = {"cast_command",},
            summary = "キャスト(猫)更新API",
            description = "キャスト(猫)を1件更新する",
            operationId = "castCatUpdate",
            responses = {
                    @ApiResponse(responseCode = "200", description = "正常系", content = @Content(schema = @Schema(implementation = PatchObject.class))),
                    @ApiResponse(responseCode = "400", description = "バリデーションエラー", content = @Content(schema = @Schema(implementation = ValidationResult.class))),
                    @ApiResponse(responseCode = "404", description = "Idが見つからない"),
                    @ApiResponse(responseCode = "409", description = "排他失敗"),
            }
    )
    @Override
    public Mono<ResponseEntity<PatchObject>> castCatUpdate(
            @Parameter(description = "カフェ識別子", schema = @Schema(allowableValues = {"cats"})) String cats,
            Integer castCatId,
            @Valid Mono<CastCatUpdate> castCatUpdate,
            ServerWebExchange exchange) {
        return null;
    }

    @Operation(
            tags = {"cast_command",},
            summary = "キャスト登録API",
            description = "キャストを1件登録する",
            operationId = "castCreate",
            responses = {
                    @ApiResponse(responseCode = "200", description = "正常系", content = @Content(schema = @Schema(implementation = PostObject.class))),
                    @ApiResponse(responseCode = "400", description = "バリデーションエラー", content = @Content(schema = @Schema(implementation = ValidationResult.class))),
            }
    )
    @Override
    public Mono<ResponseEntity<PostObject>> castCreate(
            @Parameter(description = "カフェ識別子", schema = @Schema(allowableValues = {"cats"})) String cats,
            @Parameter(description = "店舗ID", schema = @Schema(type = "integer")) Integer storeId,
            @Valid Mono<CastCreate> castCreate,
            ServerWebExchange exchange) {
        return castCreate
                .map(new CastCreateRequestMapper(cats, storeId))
                .map(this.castResisterService::promise)
                .flatMap(Mono::fromCompletionStage)
                .map(new CastCreateResponseMapper())
                .map(ResponseEntity.status(HttpStatus.OK)::body);
    }

    @Operation(
            tags = {"cast_command",},
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
    public Mono<ResponseEntity<Void>> castDelete(
            @Parameter(description = "カフェ識別子", schema = @Schema(allowableValues = {"cats"})) String cats,
            @Parameter(description = "店舗ID", schema = @Schema(type = "integer")) Integer storeId,
            Integer castId,
            @NotNull @Valid Integer version,
            ServerWebExchange exchange) {
        return null;
    }

    @Operation(
            tags = {"cast_command",},
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
    public Mono<ResponseEntity<PatchObject>> castUpdate(
            @Parameter(description = "カフェ識別子", schema = @Schema(allowableValues = {"cats"})) String cats,
            @Parameter(description = "店舗ID", schema = @Schema(type = "integer")) Integer storeId,
            Integer castId,
            @Valid Mono<CastUpdate> castUpdate,
            ServerWebExchange exchange) {
        return null;
    }

}