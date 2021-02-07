package mabubu0203.com.github.catcafe.api.controller.event;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.openapitools.api.EventCommandApi;
import org.openapitools.model.EventCreate;
import org.openapitools.model.EventUpdate;
import org.openapitools.model.PatchObject;
import org.openapitools.model.PostObject;
import org.openapitools.model.ValidationResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class EventCommandApiController implements EventCommandApi {

  @Operation(
      tags = {"event_command",},
      summary = "イベント登録API",
      description = "イベントを1件登録する",
      operationId = "eventCreate",
      responses = {
          @ApiResponse(responseCode = "200", description = "正常系", content = @Content(schema = @Schema(implementation = PostObject.class))),
          @ApiResponse(responseCode = "400", description = "バリデーションエラー", content = @Content(schema = @Schema(implementation = ValidationResult.class))),
      }
  )
  @Override
  public Mono<ResponseEntity<PostObject>> eventCreate(
      String cats,
      @Valid Mono<EventCreate> eventCreate,
      ServerWebExchange exchange) {
    return null;
  }

  @Operation(
      tags = {"event_command",},
      summary = "イベント削除API",
      description = "イベントを1件論理削除する",
      operationId = "eventDelete",
      responses = {
          @ApiResponse(responseCode = "204", description = "正常系"),
          @ApiResponse(responseCode = "404", description = "Idが見つからない"),
          @ApiResponse(responseCode = "409", description = "排他失敗"),
      }
  )
  @Override
  public Mono<ResponseEntity<Void>> eventDelete(
      String cats,
      Integer eventId,
      @NotNull @Valid Integer version,
      ServerWebExchange exchange) {
    return null;
  }

  @Operation(
      tags = {"event_command",},
      summary = "イベント更新API",
      description = "イベントを1件更新する",
      operationId = "eventUpdate",
      responses = {
          @ApiResponse(responseCode = "200", description = "正常系", content = @Content(schema = @Schema(implementation = PatchObject.class))),
          @ApiResponse(responseCode = "400", description = "バリデーションエラー", content = @Content(schema = @Schema(implementation = ValidationResult.class))),
          @ApiResponse(responseCode = "404", description = "Idが見つからない"),
          @ApiResponse(responseCode = "409", description = "排他失敗"),
      }
  )
  @Override
  public Mono<ResponseEntity<PatchObject>> eventUpdate(
      String cats,
      Integer eventId,
      @Valid Mono<EventUpdate> eventUpdate,
      ServerWebExchange exchange) {
    return null;
  }

}
