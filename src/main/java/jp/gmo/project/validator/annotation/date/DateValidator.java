package jp.gmo.project.validator.annotation.date;

import jp.gmo.project.constant.Constants;
import jp.gmo.project.utils.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraintvalidation.HibernateConstraintValidator;
import org.hibernate.validator.constraintvalidation.HibernateConstraintValidatorInitializationContext;

import javax.validation.ConstraintValidatorContext;
import javax.validation.metadata.ConstraintDescriptor;

public class DateValidator implements HibernateConstraintValidator<ValidDate, String> {

    private String pattern;

    @Override
    public void initialize(ConstraintDescriptor<ValidDate> constraintDescriptor,
                           HibernateConstraintValidatorInitializationContext initializationContext) {

        HibernateConstraintValidator.super.initialize(constraintDescriptor, initializationContext);

        this.pattern = (String) constraintDescriptor.getAttributes().get(Constants.CONST_STR_PATTERN);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        if (StringUtils.isEmpty(value)) {
            return true;
        }
        return DateUtils.isDateValid(value, this.pattern);
    }

}
