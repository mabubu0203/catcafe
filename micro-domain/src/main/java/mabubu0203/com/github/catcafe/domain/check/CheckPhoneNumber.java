package mabubu0203.com.github.catcafe.domain.check;

import mabubu0203.com.github.catcafe.domain.check.validator.PhoneNumberValidator;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {PhoneNumberValidator.class})
public @interface CheckPhoneNumber {

  //表示するエラーメッセージ(アノテーション属性で指定)
  String message() default "";

  //特定のバリデーショングループがカスタマイズできるような設定
  Class<?>[] groups() default {};

  //チェック対象のオブジェクトになんらかのメタ情報を与えるためだけの宣言
  Class<? extends Payload>[] payload() default {};

}
