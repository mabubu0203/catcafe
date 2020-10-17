package mabubu0203.com.github.catcafe.api.controller.event;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.openapitools.api.EventQueryApi;
import org.openapitools.model.EventFindResponse;
import org.openapitools.model.EventSearchResponse;
import org.openapitools.model.ValidationResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class EventQueryApiController implements EventQueryApi {

    @Operation(
            tags = {"event_query",},
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
    public Mono<ResponseEntity<EventFindResponse>> eventFind(
            String cats,
            Integer eventId,
            ServerWebExchange exchange) {
        return null;
    }

    @Operation(
            tags = {"event_query",},
            summary = "イベント一覧取得API",
            description = "イベントを取得する",
            operationId = "eventSearch",
            responses = {
                    @ApiResponse(responseCode = "200", description = "正常系", content = @Content(schema = @Schema(implementation = EventSearchResponse.class))),
                    @ApiResponse(responseCode = "400", description = "バリデーションエラー", content = @Content(schema = @Schema(implementation = ValidationResult.class))),
            }
    )
    @Override
    public Mono<ResponseEntity<EventSearchResponse>> eventSearch(
            String cats,
            @Valid List<Integer> storeIds,
            ServerWebExchange exchange) {
        return null;
    }

}
