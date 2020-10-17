package mabubu0203.com.github.catcafe.api.controller.provide.service;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.openapitools.api.ProvideServiceCommandApi;
import org.openapitools.model.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequiredArgsConstructor
public class ProvideServiceCommandApiController implements ProvideServiceCommandApi {

    @Operation(
            tags = {"provide_service_command",},
            summary = "提供サービス登録API",
            description = "提供サービスを1件登録する",
            operationId = "provideServiceCreate",
            responses = {
                    @ApiResponse(responseCode = "200", description = "正常系", content = @Content(schema = @Schema(implementation = PostObject.class))),
                    @ApiResponse(responseCode = "400", description = "バリデーションエラー", content = @Content(schema = @Schema(implementation = ValidationResult.class))),
            }
    )
    @Override
    public Mono<ResponseEntity<PostObject>> provideServiceCreate(
            @Parameter(description = "カフェ識別子", schema = @Schema(allowableValues = {"cats"})) String cats,
            Integer storeId,
            @Valid Mono<ProvideServiceCreate> provideServiceCreate,
            ServerWebExchange exchange) {
        return null;
    }

    @Operation(
            tags = {"provide_service_command",},
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
    public Mono<ResponseEntity<Void>> provideServiceDelete(
            @Parameter(description = "カフェ識別子", schema = @Schema(allowableValues = {"cats"})) String cats,
            Integer storeId,
            Integer provideServiceId,
            @NotNull @Valid Integer version,
            ServerWebExchange exchange) {
        return null;
    }

    @Operation(
            tags = {"provide_service_command",},
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
    public Mono<ResponseEntity<PatchObject>> provideServiceUpdate(
            @Parameter(description = "カフェ識別子", schema = @Schema(allowableValues = {"cats"})) String cats,
            Integer storeId,
            Integer provideServiceId,
            @Valid Mono<ProvideServiceUpdate> provideServiceUpdate,
            ServerWebExchange exchange) {
        return null;
    }

}
