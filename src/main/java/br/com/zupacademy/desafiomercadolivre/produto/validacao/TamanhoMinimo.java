package br.com.zupacademy.desafiomercadolivre.produto.validacao;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = {TamanhoMinimoValidator.class})
@Retention(RetentionPolicy.RUNTIME)
public @interface TamanhoMinimo {

    String message() default "O produto deve ter pelo menos três características!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
