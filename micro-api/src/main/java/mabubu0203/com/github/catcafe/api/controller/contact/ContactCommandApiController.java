package mabubu0203.com.github.catcafe.api.controller.contact;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.openapitools.api.ContactCommandApi;
import org.openapitools.model.ContactCreateRequest;
import org.openapitools.model.InlineResponse400;
import org.openapitools.model.InlineResponse401;
import org.openapitools.model.PostObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class ContactCommandApiController implements ContactCommandApi {

  @Operation(
      tags = {"contact_command",},
      summary = "お問い合わせ登録API",
      description = "お問い合わせを1件登録する",
      operationId = "contactCreate",
      security = {@SecurityRequirement(name = "ApiKeyAuth"),},
      responses = {
          @ApiResponse(responseCode = "200", description = "正常系", content = @Content(schema = @Schema(implementation = PostObject.class))),
          @ApiResponse(responseCode = "400", description = "バリデーションエラー", content = @Content(schema = @Schema(implementation = InlineResponse400.class))),
          @ApiResponse(responseCode = "401", description = "認証エラー", content = @Content(schema = @Schema(implementation = InlineResponse401.class))),
      }
  )
  @Override
  public Mono<ResponseEntity<PostObject>> contactCreate(
      String cats,
      @Valid Mono<ContactCreateRequest> contactCreate,
      ServerWebExchange exchange) {
    return null;
  }

}
