package mabubu0203.com.github.catcafe.api.controller.frequently.asked.question;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.openapitools.api.FrequentlyAskedQuestionCommandApi;
import org.openapitools.model.AuthenticationResult;
import org.openapitools.model.FrequentlyAskedQuestionCreate;
import org.openapitools.model.FrequentlyAskedQuestionUpdate;
import org.openapitools.model.PatchObject;
import org.openapitools.model.PostObject;
import org.openapitools.model.ValidationResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class FrequentlyAskedQuestionCommandApiController implements
    FrequentlyAskedQuestionCommandApi {

  @Operation(
      tags = {"frequently_asked_question_command",},
      summary = "FAQ登録API",
      description = "FAQを1件登録する",
      operationId = "frequentlyAskedQuestionCreate",
      security = {@SecurityRequirement(name = "ApiKeyAuth"),},
      responses = {
          @ApiResponse(responseCode = "200", description = "正常系", content = @Content(schema = @Schema(implementation = PostObject.class))),
          @ApiResponse(responseCode = "400", description = "バリデーションエラー", content = @Content(schema = @Schema(implementation = ValidationResult.class))),
          @ApiResponse(responseCode = "401", description = "認証エラー", content = @Content(schema = @Schema(implementation = AuthenticationResult.class))),
      }
  )
  @Override
  public Mono<ResponseEntity<PostObject>> frequentlyAskedQuestionCreate(
      @Parameter(description = "カフェ識別子", schema = @Schema(allowableValues = {"cats"})) String cats,
      @Valid Mono<FrequentlyAskedQuestionCreate> frequentlyAskedQuestionCreate,
      ServerWebExchange exchange) {
    return null;
  }

  @Operation(
      tags = {"frequently_asked_question_command",},
      summary = "FAQ削除API",
      description = "FAQを1件論理削除する",
      operationId = "frequentlyAskedQuestionDelete",
      security = {@SecurityRequirement(name = "ApiKeyAuth"),},
      responses = {
          @ApiResponse(responseCode = "204", description = "正常系"),
          @ApiResponse(responseCode = "401", description = "認証エラー", content = @Content(schema = @Schema(implementation = AuthenticationResult.class))),
          @ApiResponse(responseCode = "404", description = "Idが見つからない"),
          @ApiResponse(responseCode = "409", description = "排他失敗"),
      }
  )
  @Override
  public Mono<ResponseEntity<Void>> frequentlyAskedQuestionDelete(
      @Parameter(description = "カフェ識別子", schema = @Schema(allowableValues = {"cats"})) String cats,
      Integer faqId,
      @NotNull @Valid Integer version,
      ServerWebExchange exchange) {
    return null;
  }

  @Operation(
      tags = {"frequently_asked_question_command",},
      summary = "FAQ更新API",
      description = "FAQを1件更新する",
      operationId = "frequentlyAskedQuestionUpdate",
      security = {@SecurityRequirement(name = "ApiKeyAuth"),},
      responses = {
          @ApiResponse(responseCode = "200", description = "正常系", content = @Content(schema = @Schema(implementation = PatchObject.class))),
          @ApiResponse(responseCode = "400", description = "バリデーションエラー", content = @Content(schema = @Schema(implementation = ValidationResult.class))),
          @ApiResponse(responseCode = "401", description = "認証エラー", content = @Content(schema = @Schema(implementation = AuthenticationResult.class))),
          @ApiResponse(responseCode = "404", description = "Idが見つからない"),
          @ApiResponse(responseCode = "409", description = "排他失敗"),
      }
  )
  @Override
  public Mono<ResponseEntity<PatchObject>> frequentlyAskedQuestionUpdate(
      @Parameter(description = "カフェ識別子", schema = @Schema(allowableValues = {"cats"})) String cats,
      Integer faqId,
      @Valid Mono<FrequentlyAskedQuestionUpdate> frequentlyAskedQuestionUpdate,
      ServerWebExchange exchange) {
    return null;
  }

}
