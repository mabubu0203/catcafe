package mabubu0203.com.github.catcafe.domain.value.cast;

import java.util.Arrays;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum EmploymentStatus {
  MAIN(0, "main"),
  SUB(1, "sub"),
  ;
  private final int code;
  private final String label;

  /**
   * 指定されたDB管理コードのEmploymentStatusを取得します。
   *
   * @param code DB管理コード
   * @return EmploymentStatus
   */
  public static EmploymentStatus getByCode(Integer code) {
    return Optional.ofNullable(code)
        .flatMap(val ->
            Arrays.stream(EmploymentStatus.values())
                .filter(status -> status.getCode() == val)
                .findFirst())
        .orElse(EmploymentStatus.SUB);
  }

  /**
   * 指定されたDB管理ラベルのEmploymentStatusを取得します。
   *
   * @param label DB管理ラベル
   * @return EmploymentStatus
   */
  public static EmploymentStatus getByLabel(String label) {
    return Optional.ofNullable(label)
        .flatMap(val ->
            Arrays.stream(EmploymentStatus.values())
                .filter(status -> status.getLabel().equals(val))
                .findFirst())
        .orElse(EmploymentStatus.SUB);
  }

}
