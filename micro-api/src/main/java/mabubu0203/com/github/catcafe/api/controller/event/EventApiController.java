package mabubu0203.com.github.catcafe.api.controller.event;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.openapitools.api.EventApi;
import org.openapitools.model.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequiredArgsConstructor
public class EventApiController implements EventApi {

    @Operation(
            tags = {"event",},
            summary = "イベント登録API",
            description = "イベントを1件登録する",
            operationId = "eventCreate",
            responses = {
                    @ApiResponse(responseCode = "200", description = "正常系", content = @Content(schema = @Schema(implementation = PostObject.class))),
                    @ApiResponse(responseCode = "400", description = "バリデーションエラー", content = @Content(schema = @Schema(implementation = ValidationResult.class))),
            }
    )
    @Override
    public CompletableFuture<ResponseEntity<PostObject>> eventCreate(String cats, @Valid EventCreate eventCreate) {
        return null;
    }

    @Operation(
            tags = {"event",},
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
    public CompletableFuture<ResponseEntity<Void>> eventDelete(String cats, Integer eventId, @NotNull @Valid Integer version) {
        return null;
    }

    @Operation(
            tags = {"event",},
            summary = "イベント詳細取得API",
            description = "イベント詳細を1件取得する",
            operationId = "eventFind",
            responses = {
                    @ApiResponse(responseCode = "200", description = "正常系", content = @Content(schema = @Schema(implementation = EventFindResponse.class))),
                    @ApiResponse(responseCode = "400", description = "バリデーションエラー", content = @Content(schema = @Schema(implementation = ValidationResult.class))),
                    @ApiResponse(responseCode = "404", description = "Idが見つからない"),
            }
    )
    @Override
    public CompletableFuture<ResponseEntity<EventFindResponse>> eventFind(String cats, Integer eventId) {
        return null;
    }

    @Operation(
            tags = {"event",},
            summary = "イベント一覧取得API",
            description = "イベントを取得する",
            operationId = "eventSearch",
            responses = {
                    @ApiResponse(responseCode = "200", description = "正常系", content = @Content(schema = @Schema(implementation = EventSearchResponse.class))),
                    @ApiResponse(responseCode = "400", description = "バリデーションエラー", content = @Content(schema = @Schema(implementation = ValidationResult.class))),
            }
    )
    @Override
    public CompletableFuture<ResponseEntity<EventSearchResponse>> eventSearch(String cats, @Valid List<Integer> storeIds) {
        return null;
    }

    @Operation(
            tags = {"event",},
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
    public CompletableFuture<ResponseEntity<PatchObject>> eventUpdate(String cats, Integer eventId, @Valid EventUpdate eventUpdate) {
        return null;
    }

}
