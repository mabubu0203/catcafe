package mabubu0203.com.github.catcafe.api.controller.notice;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import mabubu0203.com.github.catcafe.api.controller.notice.helper.request.NoticeSearchRequestMapper;
import mabubu0203.com.github.catcafe.api.controller.notice.helper.response.NoticeSearchResponseMapper;
import mabubu0203.com.github.catcafe.api.controller.notice.service.NoticeSearchService;
import org.openapitools.api.NoticeQueryApi;
import org.openapitools.model.AuthenticationResult;
import org.openapitools.model.NoticeFindResponse;
import org.openapitools.model.NoticeSearchResponse;
import org.openapitools.model.ValidationErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class NoticeQueryApiController implements NoticeQueryApi {

  private final NoticeSearchService searchService;

  @Operation(
      tags = {"notice_query",},
      summary = "お知らせ詳細取得API",
      description = "お知らせを1件取得する",
      operationId = "noticeFind",
      security = {@SecurityRequirement(name = "ApiKeyAuth"),},
      responses = {
          @ApiResponse(responseCode = "200", description = "正常系", content = @Content(schema = @Schema(implementation = NoticeFindResponse.class))),
          @ApiResponse(responseCode = "400", description = "バリデーションエラー", content = @Content(schema = @Schema(implementation = ValidationErrorResponse.class))),
          @ApiResponse(responseCode = "401", description = "認証エラー", content = @Content(schema = @Schema(implementation = AuthenticationResult.class))),
          @ApiResponse(responseCode = "404", description = "Idが見つからない"),
      }
  )
  @Override
  public Mono<ResponseEntity<NoticeFindResponse>> noticeFind(
      @Parameter(description = "カフェ識別子", schema = @Schema(allowableValues = {"cats"})) String cats,
      Integer noticeId,
      ServerWebExchange exchange) {
    return null;
  }

  @Operation(
      tags = {"notice_query",},
      summary = "お知らせ一覧取得API",
      description = "お知らせを取得する",
      operationId = "noticeSearch",
      security = {@SecurityRequirement(name = "ApiKeyAuth"),},
      responses = {
          @ApiResponse(responseCode = "200", description = "正常系", content = @Content(schema = @Schema(implementation = NoticeSearchResponse.class))),
          @ApiResponse(responseCode = "400", description = "バリデーションエラー", content = @Content(schema = @Schema(implementation = ValidationErrorResponse.class))),
          @ApiResponse(responseCode = "401", description = "認証エラー", content = @Content(schema = @Schema(implementation = AuthenticationResult.class))),
      }
  )
  @CrossOrigin
  @Override
  public Mono<ResponseEntity<NoticeSearchResponse>> noticeSearch(
      @Parameter(description = "カフェ識別子", schema = @Schema(allowableValues = {"cats"})) String cats,
      @Parameter(description = "店舗ID") @Valid List<Integer> storeIds,
      @Parameter(description = "お知らせID") @Valid List<Integer> noticeIds,
      @Parameter(description = "取得ページ", schema = @Schema(type = "integer", maxProperties = 100)) @Valid @Min(0) @Max(100) Integer page,
      @Parameter(description = "取得サイズ", schema = @Schema(type = "integer", minProperties = 1, maxProperties = 20)) @Valid @Min(1) @Max(20) Integer size,
      @Parameter(description = "ソートキー", array = @ArraySchema(schema = @Schema(allowableValues = {
          "store_id.asc", "store_id.desc"}))) @Valid List<String> sortKeys,
      ServerWebExchange exchange) {
    return
        new NoticeSearchRequestMapper(
            cats, storeIds, noticeIds,
            page, size, sortKeys).get()
            .map(this.searchService::promise)
            .flatMap(Mono::fromCompletionStage)
            .map(new NoticeSearchResponseMapper())
            .map(ResponseEntity.status(HttpStatus.OK)::body);
  }

}
