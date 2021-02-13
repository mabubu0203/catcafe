package mabubu0203.com.github.catcafe.api.controller.contact;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.openapitools.api.ContactQueryApi;
import org.openapitools.model.AuthenticationResult;
import org.openapitools.model.ContactFindResponse;
import org.openapitools.model.ContactSearchResponse;
import org.openapitools.model.ValidationErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class ContactQueryApiController implements ContactQueryApi {

  @Operation(
      tags = {"contact_query",},
      summary = "お問い合わせ詳細取得API",
      description = "お問い合わせ詳細を1件取得する",
      operationId = "contactFind",
      security = {@SecurityRequirement(name = "ApiKeyAuth"),},
      responses = {
          @ApiResponse(responseCode = "200", description = "正常系", content = @Content(schema = @Schema(implementation = ContactFindResponse.class))),
          @ApiResponse(responseCode = "400", description = "バリデーションエラー", content = @Content(schema = @Schema(implementation = ValidationErrorResponse.class))),
          @ApiResponse(responseCode = "401", description = "認証エラー", content = @Content(schema = @Schema(implementation = AuthenticationResult.class))),
          @ApiResponse(responseCode = "404", description = "Idが見つからない"),
      }
  )
  @Override
  public Mono<ResponseEntity<ContactFindResponse>> contactFind(
      String cats,
      Integer contactId,
      ServerWebExchange exchange) {
    return null;
  }

  @Operation(
      tags = {"contact_query",},
      summary = "お問い合わせ一覧取得API",
      description = "お問い合わせを取得する",
      operationId = "contactSearch",
      security = {@SecurityRequirement(name = "ApiKeyAuth"),},
      responses = {
          @ApiResponse(responseCode = "200", description = "正常系", content = @Content(schema = @Schema(implementation = ContactSearchResponse.class))),
          @ApiResponse(responseCode = "400", description = "バリデーションエラー", content = @Content(schema = @Schema(implementation = ValidationErrorResponse.class))),
          @ApiResponse(responseCode = "401", description = "認証エラー", content = @Content(schema = @Schema(implementation = AuthenticationResult.class))),
      }
  )
  @Override
  public Mono<ResponseEntity<ContactSearchResponse>> contactSearch(
      String cats,
      @Valid List<Integer> storeIds,
      ServerWebExchange exchange) {
    return null;
  }

}
