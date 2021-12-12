package mabubu0203.com.github.catcafe.domain.value.cast;

import java.util.Arrays;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CatSex {
  UNKNOWN(0, "unknown"),
  MALE(1, "male"),
  FEMALE(2, "female"),
  ;

  private final int code;
  private final String label;

  /**
   * 指定されたDB管理コードのCatSexを取得します。
   *
   * @param code DB管理コード
   * @return CatSex
   */
  public static CatSex getByCode(Integer code) {
    return Optional.ofNullable(code)
        .flatMap(val ->
            Arrays.stream(CatSex.values())
                .filter(catSex -> catSex.getCode() == val)
                .findFirst())
        .orElse(CatSex.UNKNOWN);
  }

  /**
   * 指定されたDB管理ラベルのCatSexを取得します。
   *
   * @param label DB管理ラベル
   * @return CatSex
   */
  public static CatSex getByLabel(String label) {
    return Optional.ofNullable(label)
        .flatMap(val ->
            Arrays.stream(CatSex.values())
                .filter(catSex -> catSex.getLabel().equals(val))
                .findFirst())
        .orElse(CatSex.UNKNOWN);
  }

}
