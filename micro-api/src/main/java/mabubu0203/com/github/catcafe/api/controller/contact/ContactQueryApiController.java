package mabubu0203.com.github.catcafe.api.controller.contact;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.openapitools.api.ContactQueryApi;
import org.openapitools.model.ContactFindResponse;
import org.openapitools.model.ContactSearchResponse;
import org.openapitools.model.ValidationResult;
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
      responses = {
          @ApiResponse(responseCode = "200", description = "正常系", content = @Content(schema = @Schema(implementation = ContactFindResponse.class))),
          @ApiResponse(responseCode = "400", description = "バリデーションエラー", content = @Content(schema = @Schema(implementation = ValidationResult.class))),
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
      responses = {
          @ApiResponse(responseCode = "200", description = "正常系", content = @Content(schema = @Schema(implementation = ContactSearchResponse.class))),
          @ApiResponse(responseCode = "400", description = "バリデーションエラー", content = @Content(schema = @Schema(implementation = ValidationResult.class))),
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
