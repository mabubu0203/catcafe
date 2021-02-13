package mabubu0203.com.github.catcafe.api.controller.notice;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import mabubu0203.com.github.catcafe.api.controller.notice.helper.request.NoticeCreateRequestMapper;
import mabubu0203.com.github.catcafe.api.controller.notice.helper.response.NoticeCreateResponseMapper;
import mabubu0203.com.github.catcafe.api.controller.notice.service.NoticeResisterService;
import org.openapitools.api.NoticeCommandApi;
import org.openapitools.model.AuthenticationResult;
import org.openapitools.model.NoticeCreate;
import org.openapitools.model.NoticeUpdate;
import org.openapitools.model.PatchObject;
import org.openapitools.model.PostObject;
import org.openapitools.model.ValidationErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class NoticeCommandApiController implements NoticeCommandApi {

  private final NoticeResisterService resisterService;

  @Operation(
      tags = {"notice_command",},
      summary = "お知らせ登録API",
      description = "お知らせを1件登録する",
      operationId = "noticeCreate",
      security = {@SecurityRequirement(name = "ApiKeyAuth"),},
      responses = {
          @ApiResponse(responseCode = "200", description = "正常系", content = @Content(schema = @Schema(implementation = PostObject.class))),
          @ApiResponse(responseCode = "400", description = "バリデーションエラー", content = @Content(schema = @Schema(implementation = ValidationErrorResponse.class))),
          @ApiResponse(responseCode = "401", description = "認証エラー", content = @Content(schema = @Schema(implementation = AuthenticationResult.class))),
      }
  )
  @Override
  public Mono<ResponseEntity<PostObject>> noticeCreate(
      @Parameter(description = "カフェ識別子", schema = @Schema(allowableValues = {"cats"})) String cats,
      @Valid Mono<NoticeCreate> noticeCreate,
      ServerWebExchange exchange) {
    return noticeCreate
        .map(new NoticeCreateRequestMapper(cats))
        .map(this.resisterService::promise)
        .flatMap(Mono::fromCompletionStage)
        .map(new NoticeCreateResponseMapper())
        .map(ResponseEntity.status(HttpStatus.OK)::body);
  }

  @Operation(
      tags = {"notice_command",},
      summary = "お知らせ削除API",
      description = "お知らせを1件論理削除する",
      operationId = "noticeDelete",
      security = {@SecurityRequirement(name = "ApiKeyAuth"),},
      responses = {
          @ApiResponse(responseCode = "204", description = "正常系"),
          @ApiResponse(responseCode = "401", description = "認証エラー", content = @Content(schema = @Schema(implementation = AuthenticationResult.class))),
          @ApiResponse(responseCode = "404", description = "Idが見つからない"),
          @ApiResponse(responseCode = "409", description = "排他失敗"),
      }
  )
  @Override
  public Mono<ResponseEntity<Void>> noticeDelete(
      @Parameter(description = "カフェ識別子", schema = @Schema(allowableValues = {"cats"})) String cats,
      Integer noticeId,
      @NotNull @Valid Integer version,
      ServerWebExchange exchange) {
    return null;
  }

  @Operation(
      tags = {"notice_command",},
      summary = "お知らせ更新API",
      description = "お知らせを1件更新する",
      operationId = "noticeUpdate",
      security = {@SecurityRequirement(name = "ApiKeyAuth"),},
      responses = {
          @ApiResponse(responseCode = "200", description = "正常系", content = @Content(schema = @Schema(implementation = PatchObject.class))),
          @ApiResponse(responseCode = "400", description = "バリデーションエラー", content = @Content(schema = @Schema(implementation = ValidationErrorResponse.class))),
          @ApiResponse(responseCode = "401", description = "認証エラー", content = @Content(schema = @Schema(implementation = AuthenticationResult.class))),
          @ApiResponse(responseCode = "404", description = "Idが見つからない"),
          @ApiResponse(responseCode = "409", description = "排他失敗"),
      }
  )
  @Override
  public Mono<ResponseEntity<PatchObject>> noticeUpdate(
      @Parameter(description = "カフェ識別子", schema = @Schema(allowableValues = {"cats"})) String cats,
      Integer noticeId,
      @Valid Mono<NoticeUpdate> noticeUpdate,
      ServerWebExchange exchange) {
    return null;
  }

}
