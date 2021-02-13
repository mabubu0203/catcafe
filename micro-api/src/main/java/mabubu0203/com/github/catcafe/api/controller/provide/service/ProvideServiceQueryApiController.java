package mabubu0203.com.github.catcafe.api.controller.provide.service;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import mabubu0203.com.github.catcafe.api.controller.provide.service.helper.request.ProvideServiceSearchRequestMapper;
import mabubu0203.com.github.catcafe.api.controller.provide.service.helper.response.ProvideServiceSearchResponseMapper;
import mabubu0203.com.github.catcafe.api.controller.provide.service.service.ProvideServiceSearchService;
import org.openapitools.api.ProvideServiceQueryApi;
import org.openapitools.model.InlineResponse400;
import org.openapitools.model.InlineResponse401;
import org.openapitools.model.ProvideServiceFindResponse;
import org.openapitools.model.ProvideServiceSearchResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class ProvideServiceQueryApiController implements ProvideServiceQueryApi {

  private final ProvideServiceSearchService searchService;

  @Operation(
      tags = {"provide_service_query",},
      summary = "提供サービス詳細取得API",
      description = "提供サービス詳細を1件取得する",
      operationId = "provideServiceFind",
      security = {@SecurityRequirement(name = "ApiKeyAuth"),},
      responses = {
          @ApiResponse(responseCode = "200", description = "正常系", content = @Content(schema = @Schema(implementation = ProvideServiceFindResponse.class))),
          @ApiResponse(responseCode = "400", description = "バリデーションエラー", content = @Content(schema = @Schema(implementation = InlineResponse400.class))),
          @ApiResponse(responseCode = "401", description = "認証エラー", content = @Content(schema = @Schema(implementation = InlineResponse401.class))),
          @ApiResponse(responseCode = "404", description = "Idが見つからない"),
      }
  )
  @Override
  public Mono<ResponseEntity<ProvideServiceFindResponse>> provideServiceFind(
      @Parameter(description = "カフェ識別子", schema = @Schema(allowableValues = {"cats"})) String cats,
      Integer storeId,
      Integer provideServiceId,
      ServerWebExchange exchange) {
    return null;
  }

  @Operation(
      tags = {"provide_service_query",},
      summary = "提供サービス一覧取得API",
      description = "提供サービスを取得する",
      operationId = "provideServiceSearch",
      security = {@SecurityRequirement(name = "ApiKeyAuth"),},
      responses = {
          @ApiResponse(responseCode = "200", description = "正常系", content = @Content(schema = @Schema(implementation = ProvideServiceSearchResponse.class))),
          @ApiResponse(responseCode = "400", description = "バリデーションエラー", content = @Content(schema = @Schema(implementation = InlineResponse400.class))),
          @ApiResponse(responseCode = "401", description = "認証エラー", content = @Content(schema = @Schema(implementation = InlineResponse401.class))),
      }
  )
  @CrossOrigin
  @Override
  public Mono<ResponseEntity<ProvideServiceSearchResponse>> provideServiceSearch(
      @Parameter(description = "カフェ識別子", schema = @Schema(allowableValues = {"cats"})) String cats,
      @Parameter(description = "店舗ID") @Valid List<Integer> storeIds,
      ServerWebExchange exchange) {
    return
        new ProvideServiceSearchRequestMapper(
            cats, storeIds).get()
            .map(this.searchService::promise)
            .flatMap(Mono::fromCompletionStage)
            .map(new ProvideServiceSearchResponseMapper())
            .map(ResponseEntity.status(HttpStatus.OK)::body);
  }

}
