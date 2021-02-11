package mabubu0203.com.github.catcafe.api.controller.display.menu;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.openapitools.api.DisplayMenuCommandApi;
import org.openapitools.model.AuthenticationResult;
import org.openapitools.model.DisplayMenuCreate;
import org.openapitools.model.DisplayMenuUpdate;
import org.openapitools.model.PatchObject;
import org.openapitools.model.PostObject;
import org.openapitools.model.ValidationResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class DisplayMenuCommandApiController implements DisplayMenuCommandApi {

  @Operation(
      tags = {"display_menu_command",},
      summary = "表示メニュー登録API",
      description = "表示メニューを1件登録する",
      operationId = "displayMenuCreate",
      security = {@SecurityRequirement(name = "ApiKeyAuth"),},
      responses = {
          @ApiResponse(responseCode = "200", description = "正常系", content = @Content(schema = @Schema(implementation = PostObject.class))),
          @ApiResponse(responseCode = "400", description = "バリデーションエラー", content = @Content(schema = @Schema(implementation = ValidationResult.class))),
          @ApiResponse(responseCode = "401", description = "認証エラー", content = @Content(schema = @Schema(implementation = AuthenticationResult.class))),
      }
  )
  @Override
  public Mono<ResponseEntity<PostObject>> displayMenuCreate(
      String cats,
      Integer storeId,
      @Valid Mono<DisplayMenuCreate> displayMenuCreate,
      ServerWebExchange exchange) {
    return null;
  }

  @Operation(
      tags = {"display_menu_command",},
      summary = "表示メニュー削除API",
      description = "表示メニューを1件論理削除する",
      operationId = "displayMenuDelete",
      security = {@SecurityRequirement(name = "ApiKeyAuth"),},
      responses = {
          @ApiResponse(responseCode = "204", description = "正常系"),
          @ApiResponse(responseCode = "401", description = "認証エラー", content = @Content(schema = @Schema(implementation = AuthenticationResult.class))),
          @ApiResponse(responseCode = "404", description = "Idが見つからない"),
          @ApiResponse(responseCode = "409", description = "排他失敗"),
      }
  )
  @Override
  public Mono<ResponseEntity<Void>> displayMenuDelete(
      String cats,
      Integer storeId,
      Integer displayMenuId,
      @NotNull @Valid Integer version,
      ServerWebExchange exchange) {
    return null;
  }

  @Operation(
      tags = {"display_menu_command",},
      summary = "表示メニュー更新API",
      description = "表示メニューを1件更新する",
      operationId = "displayMenuUpdate",
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
  public Mono<ResponseEntity<PatchObject>> displayMenuUpdate(
      String cats,
      Integer storeId,
      Integer displayMenuId,
      @Valid Mono<DisplayMenuUpdate> displayMenuUpdate,
      ServerWebExchange exchange) {
    return null;
  }

}
