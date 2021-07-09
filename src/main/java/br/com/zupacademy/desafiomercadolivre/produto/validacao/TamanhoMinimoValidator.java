package br.com.zupacademy.desafiomercadolivre.produto.validacao;

import br.com.zupacademy.desafiomercadolivre.produto.Caracteristica;
import br.com.zupacademy.desafiomercadolivre.produto.CaracteristicaRequest;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class TamanhoMinimoValidator implements ConstraintValidator<TamanhoMinimo, Object> {
    @Override
    public void initialize(TamanhoMinimo constraintAnnotation) {
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
        if(value != null){
            List<CaracteristicaRequest> caracteristicasRequests = (List<CaracteristicaRequest>) value;
            return caracteristicasRequests.size() >= 3;
        }else return false;
    }
}
