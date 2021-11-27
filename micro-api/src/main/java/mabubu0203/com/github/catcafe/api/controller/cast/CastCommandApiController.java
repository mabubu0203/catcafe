package mabubu0203.com.github.catcafe.api.controller.cast;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import mabubu0203.com.github.catcafe.api.controller.cast.helper.request.CastCatCreateRequestMapper;
import mabubu0203.com.github.catcafe.api.controller.cast.helper.request.CastCreateRequestMapper;
import mabubu0203.com.github.catcafe.api.controller.cast.helper.response.CastCatCreateResponseMapper;
import mabubu0203.com.github.catcafe.api.controller.cast.helper.response.CastCreateResponseMapper;
import mabubu0203.com.github.catcafe.api.controller.cast.service.CastCatResisterService;
import mabubu0203.com.github.catcafe.api.controller.cast.service.CastRegisterService;
import org.openapitools.api.CastCommandApi;
import org.openapitools.model.CastCatCreateRequest;
import org.openapitools.model.CastCatUpdateRequest;
import org.openapitools.model.CastCreateRequest;
import org.openapitools.model.CastUpdateRequest;
import org.openapitools.model.InlineResponse400;
import org.openapitools.model.InlineResponse401;
import org.openapitools.model.PatchObject;
import org.openapitools.model.PostObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

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
      security = {@SecurityRequirement(name = "ApiKeyAuth"),},
      responses = {
          @ApiResponse(responseCode = "200", description = "正常系", content = @Content(schema = @Schema(implementation = PostObject.class)), headers = @Header(name = "Location", description = "詳細取得URL")),
          @ApiResponse(responseCode = "400", description = "バリデーションエラー", content = @Content(schema = @Schema(implementation = InlineResponse400.class))),
          @ApiResponse(responseCode = "401", description = "認証エラー", content = @Content(schema = @Schema(implementation = InlineResponse401.class))),
      }
  )
  @Override
  public Mono<ResponseEntity<PostObject>> castCatCreate(
      @Parameter(description = "カフェ識別子", schema = @Schema(allowableValues = {"cats"})) String cats,
      @Valid Mono<CastCatCreateRequest> castCatCreate,
      ServerWebExchange exchange) {
    return castCatCreate
        .map(new CastCatCreateRequestMapper(cats))
        .flatMap(this.castCatResisterService::action)
        .map(new CastCatCreateResponseMapper())
        .map(ResponseEntity.status(HttpStatus.OK).header("Location", "")::body);
  }

  @Operation(
      tags = {"cast_command",},
      summary = "キャスト(猫)削除API",
      description = "キャスト(猫)を1件論理削除する",
      operationId = "castCatDelete",
      security = {@SecurityRequirement(name = "ApiKeyAuth"),},
      responses = {
          @ApiResponse(responseCode = "204", description = "正常系"),
          @ApiResponse(responseCode = "401", description = "認証エラー", content = @Content(schema = @Schema(implementation = InlineResponse401.class))),
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
      security = {@SecurityRequirement(name = "ApiKeyAuth"),},
      responses = {
          @ApiResponse(responseCode = "200", description = "正常系", content = @Content(schema = @Schema(implementation = PatchObject.class)), headers = @Header(name = "Location", description = "詳細取得URL")),
          @ApiResponse(responseCode = "400", description = "バリデーションエラー", content = @Content(schema = @Schema(implementation = InlineResponse400.class))),
          @ApiResponse(responseCode = "401", description = "認証エラー", content = @Content(schema = @Schema(implementation = InlineResponse401.class))),
          @ApiResponse(responseCode = "404", description = "Idが見つからない"),
          @ApiResponse(responseCode = "409", description = "排他失敗"),
      }
  )
  @Override
  public Mono<ResponseEntity<PatchObject>> castCatUpdate(
      @Parameter(description = "カフェ識別子", schema = @Schema(allowableValues = {"cats"})) String cats,
      Integer castCatId,
      @Valid Mono<CastCatUpdateRequest> castCatUpdate,
      ServerWebExchange exchange) {
    return null;
  }

  @Operation(
      tags = {"cast_command",},
      summary = "キャスト登録API",
      description = "キャストを1件登録する",
      operationId = "castCreate",
      security = {@SecurityRequirement(name = "ApiKeyAuth"),},
      responses = {
          @ApiResponse(responseCode = "200", description = "正常系", content = @Content(schema = @Schema(implementation = PostObject.class)), headers = @Header(name = "Location", description = "詳細取得URL")),
          @ApiResponse(responseCode = "400", description = "バリデーションエラー", content = @Content(schema = @Schema(implementation = InlineResponse400.class))),
          @ApiResponse(responseCode = "401", description = "認証エラー", content = @Content(schema = @Schema(implementation = InlineResponse401.class))),
      }
  )
  @Override
  public Mono<ResponseEntity<PostObject>> castCreate(
      @Parameter(description = "カフェ識別子", schema = @Schema(allowableValues = {"cats"})) String cats,
      @Parameter(description = "店舗ID", schema = @Schema(type = "integer")) Integer storeId,
      @Valid Mono<CastCreateRequest> castCreate,
      ServerWebExchange exchange) {
    return castCreate
        .map(new CastCreateRequestMapper(cats, storeId))
        .flatMap(this.castResisterService::action)
        .map(new CastCreateResponseMapper())
        .map(ResponseEntity.status(HttpStatus.OK).header("Location", "")::body);
  }

  @Operation(
      tags = {"cast_command",},
      summary = "キャスト削除API",
      description = "キャストを1件論理削除する",
      operationId = "castDelete",
      security = {@SecurityRequirement(name = "ApiKeyAuth"),},
      responses = {
          @ApiResponse(responseCode = "204", description = "正常系"),
          @ApiResponse(responseCode = "401", description = "認証エラー", content = @Content(schema = @Schema(implementation = InlineResponse401.class))),
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
      security = {@SecurityRequirement(name = "ApiKeyAuth"),},
      responses = {
          @ApiResponse(responseCode = "200", description = "正常系", content = @Content(schema = @Schema(implementation = PatchObject.class)), headers = @Header(name = "Location", description = "詳細取得URL")),
          @ApiResponse(responseCode = "400", description = "バリデーションエラー", content = @Content(schema = @Schema(implementation = InlineResponse400.class))),
          @ApiResponse(responseCode = "401", description = "認証エラー", content = @Content(schema = @Schema(implementation = InlineResponse401.class))),
          @ApiResponse(responseCode = "404", description = "Idが見つからない"),
          @ApiResponse(responseCode = "409", description = "排他失敗"),
      }
  )
  @Override
  public Mono<ResponseEntity<PatchObject>> castUpdate(
      @Parameter(description = "カフェ識別子", schema = @Schema(allowableValues = {"cats"})) String cats,
      @Parameter(description = "店舗ID", schema = @Schema(type = "integer")) Integer storeId,
      Integer castId,
      @Valid Mono<CastUpdateRequest> castUpdate,
      ServerWebExchange exchange) {
    return null;
  }

}
