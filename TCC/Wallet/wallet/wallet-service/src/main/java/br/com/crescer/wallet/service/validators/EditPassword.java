package br.com.crescer.wallet.service.validators;

/**
 *
 * @author Hedo
 */
 
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

    @Target({ElementType.TYPE,ElementType.ANNOTATION_TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @Constraint(validatedBy = EditPasswordValidator.class)
    @Documented
    public @interface EditPassword {

        String message() default "As senhas não coincidem.";

        Class<?>[] groups() default {};

        Class<? extends Payload>[] payload() default {};
    }


