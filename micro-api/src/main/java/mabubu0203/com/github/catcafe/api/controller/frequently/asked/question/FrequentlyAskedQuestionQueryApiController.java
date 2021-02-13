package mabubu0203.com.github.catcafe.api.controller.frequently.asked.question;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import mabubu0203.com.github.catcafe.api.controller.frequently.asked.question.helper.request.FrequentlyAskedQuestionFindRequestMapper;
import mabubu0203.com.github.catcafe.api.controller.frequently.asked.question.helper.request.FrequentlyAskedQuestionSearchRequestMapper;
import mabubu0203.com.github.catcafe.api.controller.frequently.asked.question.helper.response.FrequentlyAskedQuestionFindResponseMapper;
import mabubu0203.com.github.catcafe.api.controller.frequently.asked.question.helper.response.FrequentlyAskedQuestionSearchResponseMapper;
import mabubu0203.com.github.catcafe.api.controller.frequently.asked.question.service.FrequentlyAskedQuestionSearchService;
import org.openapitools.api.FrequentlyAskedQuestionQueryApi;
import org.openapitools.model.AuthenticationResult;
import org.openapitools.model.FrequentlyAskedQuestionFindResponse;
import org.openapitools.model.FrequentlyAskedQuestionSearchResponse;
import org.openapitools.model.ValidationErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class FrequentlyAskedQuestionQueryApiController implements FrequentlyAskedQuestionQueryApi {

  private final FrequentlyAskedQuestionSearchService searchService;

  @Operation(
      tags = {"frequently_asked_question_query",},
      summary = "FAQ詳細取得API",
      description = "FAQ詳細を1件取得する",
      operationId = "frequentlyAskedQuestionFind",
      security = {@SecurityRequirement(name = "ApiKeyAuth"),},
      responses = {
          @ApiResponse(responseCode = "200", description = "正常系", content = @Content(schema = @Schema(implementation = FrequentlyAskedQuestionFindResponse.class))),
          @ApiResponse(responseCode = "400", description = "バリデーションエラー", content = @Content(schema = @Schema(implementation = ValidationErrorResponse.class))),
          @ApiResponse(responseCode = "401", description = "認証エラー", content = @Content(schema = @Schema(implementation = AuthenticationResult.class))),
          @ApiResponse(responseCode = "404", description = "Idが見つからない"),
      }
  )
  @Override
  public Mono<ResponseEntity<FrequentlyAskedQuestionFindResponse>> frequentlyAskedQuestionFind(
      @Parameter(description = "カフェ識別子", schema = @Schema(allowableValues = {"cats"})) String cats,
      Integer faqId,
      ServerWebExchange exchange) {
    return
        new FrequentlyAskedQuestionFindRequestMapper(
            cats, faqId).get()
            .map(this.searchService::promise)
            .flatMap(Mono::fromCompletionStage)
            .map(new FrequentlyAskedQuestionFindResponseMapper())
            .map(ResponseEntity.status(HttpStatus.OK)::body);
  }

  @Operation(
      tags = {"frequently_asked_question_query",},
      summary = "FAQ一覧取得API",
      description = "FAQを取得する",
      operationId = "frequentlyAskedQuestionSearch",
      security = {@SecurityRequirement(name = "ApiKeyAuth"),},
      responses = {
          @ApiResponse(responseCode = "200", description = "正常系", content = @Content(schema = @Schema(implementation = FrequentlyAskedQuestionSearchResponse.class))),
          @ApiResponse(responseCode = "400", description = "バリデーションエラー", content = @Content(schema = @Schema(implementation = ValidationErrorResponse.class))),
          @ApiResponse(responseCode = "401", description = "認証エラー", content = @Content(schema = @Schema(implementation = AuthenticationResult.class))),
      }
  )
  @CrossOrigin
  @Override
  public Mono<ResponseEntity<FrequentlyAskedQuestionSearchResponse>> frequentlyAskedQuestionSearch(
      @Parameter(description = "カフェ識別子", schema = @Schema(allowableValues = {"cats"})) String cats,
      @Valid List<Integer> storeIds,
      ServerWebExchange exchange) {
    return
        new FrequentlyAskedQuestionSearchRequestMapper(
            cats, storeIds).get()
            .map(this.searchService::promise)
            .flatMap(Mono::fromCompletionStage)
            .map(new FrequentlyAskedQuestionSearchResponseMapper())
            .map(ResponseEntity.status(HttpStatus.OK)::body);
  }

}
