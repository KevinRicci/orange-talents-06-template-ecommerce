package br.com.zupacademy.desafiomercadolivre.validacao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class ExistsIdValidator implements ConstraintValidator<ExistsId, Object> {

    private Class<?> klass;
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void initialize(ExistsId params) {
        this.klass = params.domainClass();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
        Query query = entityManager.createQuery("select 1 from "+klass.getName()+" where id =:value");
        query.setParameter("value", value);
        List<?> list = query.getResultList();

        return !list.isEmpty();
    }
}
