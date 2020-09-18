package mabubu0203.com.github.catcafe.api.controller.display.menu;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.openapitools.api.DisplayMenuApi;
import org.openapitools.model.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class DisplayMenuApiController implements DisplayMenuApi {

    @Operation(
            tags = {"display_menu",},
            summary = "表示メニュー登録API",
            description = "表示メニューを1件登録する",
            operationId = "displayMenuCreate",
            responses = {
                    @ApiResponse(responseCode = "200", description = "正常系", content = @Content(schema = @Schema(implementation = PostObject.class))),
                    @ApiResponse(responseCode = "400", description = "バリデーションエラー", content = @Content(schema = @Schema(implementation = ValidationResult.class))),
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
            tags = {"display_menu",},
            summary = "表示メニュー削除API",
            description = "表示メニューを1件論理削除する",
            operationId = "displayMenuDelete",
            responses = {
                    @ApiResponse(responseCode = "204", description = "正常系"),
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
            tags = {"display_menu",},
            summary = "表示メニュー詳細取得API",
            description = "表示メニュー詳細を1件取得する",
            operationId = "displayMenuFind",
            responses = {
                    @ApiResponse(responseCode = "200", description = "正常系", content = @Content(schema = @Schema(implementation = DisplayMenuFindResponse.class))),
                    @ApiResponse(responseCode = "400", description = "バリデーションエラー", content = @Content(schema = @Schema(implementation = ValidationResult.class))),
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
            tags = {"display_menu",},
            summary = "表示メニュー一覧取得API",
            description = "表示メニューを取得する",
            operationId = "displayMenuSearch",
            responses = {
                    @ApiResponse(responseCode = "200", description = "正常系", content = @Content(schema = @Schema(implementation = DisplayMenuSearchResponse.class))),
                    @ApiResponse(responseCode = "400", description = "バリデーションエラー", content = @Content(schema = @Schema(implementation = ValidationResult.class))),
            }
    )
    @Override
    public Mono<ResponseEntity<DisplayMenuSearchResponse>> displayMenuSearch(
            String cats,
            @Valid List<Integer> storeIds,
            ServerWebExchange exchange) {
        return null;
    }

    @Operation(
            tags = {"display_menu",},
            summary = "表示メニュー更新API",
            description = "表示メニューを1件更新する",
            operationId = "displayMenuUpdate",
            responses = {
                    @ApiResponse(responseCode = "200", description = "正常系", content = @Content(schema = @Schema(implementation = PatchObject.class))),
                    @ApiResponse(responseCode = "400", description = "バリデーションエラー", content = @Content(schema = @Schema(implementation = ValidationResult.class))),
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
