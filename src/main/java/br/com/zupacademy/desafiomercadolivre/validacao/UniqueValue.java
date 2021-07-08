package br.com.zupacademy.desafiomercadolivre.validacao;

import org.springframework.context.annotation.Profile;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = {UniqueValueValidator.class})
@Target(value = ElementType.FIELD)
@Retention(value = RetentionPolicy.RUNTIME)
@Profile(value = {"prod", "test"})
public @interface UniqueValue {

    String message() default "Já existe um valor igual cadastrado";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String fieldName();

    Class<?> domainClass();
}
