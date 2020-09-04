package mabubu0203.com.github.catcafe.api.controller.contact;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.openapitools.api.ContactApi;
import org.openapitools.model.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequiredArgsConstructor
public class ContactApiController implements ContactApi {

    @Operation(
            tags = {"contact",},
            summary = "お問い合わせ登録API",
            description = "お問い合わせを1件登録する",
            operationId = "contactCreate",
            responses = {
                    @ApiResponse(responseCode = "200", description = "正常系", content = @Content(schema = @Schema(implementation = PostObject.class))),
                    @ApiResponse(responseCode = "400", description = "バリデーションエラー", content = @Content(schema = @Schema(implementation = ValidationResult.class))),
            }
    )
    @Override
    public CompletableFuture<ResponseEntity<PostObject>> contactCreate(String cats, @Valid ContactCreate contactCreate) {
        return null;
    }

    @Operation(
            tags = {"contact",},
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
    public CompletableFuture<ResponseEntity<ContactFindResponse>> contactFind(String cats, Integer contactId) {
        return null;
    }

    @Operation(
            tags = {"contact",},
            summary = "お問い合わせ一覧取得API",
            description = "お問い合わせを取得する",
            operationId = "contactSearch",
            responses = {
                    @ApiResponse(responseCode = "200", description = "正常系", content = @Content(schema = @Schema(implementation = ContactSearchResponse.class))),
                    @ApiResponse(responseCode = "400", description = "バリデーションエラー", content = @Content(schema = @Schema(implementation = ValidationResult.class))),
            }
    )
    @Override
    public CompletableFuture<ResponseEntity<ContactSearchResponse>> contactSearch(String cats, @Valid List<Integer> storeIds) {
        return null;
    }

}
