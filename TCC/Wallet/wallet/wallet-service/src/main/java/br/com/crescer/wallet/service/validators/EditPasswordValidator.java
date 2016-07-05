package br.com.crescer.wallet.service.validators;

/**
 *
 * @author Hedo
 */
/*
    Retirado de
    <http://www.baeldung.com/registration-with-spring-mvc-and-spring-security>
 */
import br.com.crescer.wallet.service.dto.UsuarioDTO;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EditPasswordValidator implements ConstraintValidator<EditPassword, Object> {

    @Override
    public void initialize(EditPassword constraintAnnotation) {
    }

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context) {
        UsuarioDTO user = (UsuarioDTO) obj;
        if(user.getSenha().length() == 0) return false;
        
        if (user.getSenha().length() > 8 && user.getSenha().length() < 255 && user.getId() != 0) {
            return true;
        } else if (user.getSenha().length() > 8 && user.getSenha().length() < 255 && user.getId() == 0) {
            return true;
        }
        return false;
    }
}
