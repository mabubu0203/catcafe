package mabubu0203.com.github.catcafe.api.controller.display.menu;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.openapitools.api.DisplayMenuQueryApi;
import org.openapitools.model.AuthenticationResult;
import org.openapitools.model.DisplayMenuFindResponse;
import org.openapitools.model.DisplayMenuSearchResponse;
import org.openapitools.model.ValidationResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class DisplayMenuQueryApiController implements DisplayMenuQueryApi {

  @Operation(
      tags = {"display_menu_query",},
      summary = "表示メニュー詳細取得API",
      description = "表示メニュー詳細を1件取得する",
      operationId = "displayMenuFind",
      security = {@SecurityRequirement(name = "ApiKeyAuth"),},
      responses = {
          @ApiResponse(responseCode = "200", description = "正常系", content = @Content(schema = @Schema(implementation = DisplayMenuFindResponse.class))),
          @ApiResponse(responseCode = "400", description = "バリデーションエラー", content = @Content(schema = @Schema(implementation = ValidationResult.class))),
          @ApiResponse(responseCode = "401", description = "認証エラー", content = @Content(schema = @Schema(implementation = AuthenticationResult.class))),
          @ApiResponse(responseCode = "404", description = "Idが見つからない"),
      }
  )
  @Override
  public Mono<ResponseEntity<DisplayMenuFindResponse>> displayMenuFind(
      String cats,
      Integer storeId,
      Integer displayMenuId,
      ServerWebExchange exchange) {
    return null;
  }

  @Operation(
      tags = {"display_menu_query",},
      summary = "表示メニュー一覧取得API",
      description = "表示メニューを取得する",
      operationId = "displayMenuSearch",
      security = {@SecurityRequirement(name = "ApiKeyAuth"),},
      responses = {
          @ApiResponse(responseCode = "200", description = "正常系", content = @Content(schema = @Schema(implementation = DisplayMenuSearchResponse.class))),
          @ApiResponse(responseCode = "400", description = "バリデーションエラー", content = @Content(schema = @Schema(implementation = ValidationResult.class))),
          @ApiResponse(responseCode = "401", description = "認証エラー", content = @Content(schema = @Schema(implementation = AuthenticationResult.class))),
      }
  )
  @Override
  public Mono<ResponseEntity<DisplayMenuSearchResponse>> displayMenuSearch(
      String cats,
      @Valid List<Integer> storeIds,
      ServerWebExchange exchange) {
    return null;
  }

}
