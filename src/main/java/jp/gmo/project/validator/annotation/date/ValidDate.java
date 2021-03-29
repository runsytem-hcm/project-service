package jp.gmo.project.validator.annotation.date;

import jp.gmo.project.constant.Constants;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = {DateValidator.class})
@Target({METHOD, FIELD})
@Retention(RUNTIME)
public @interface ValidDate {
    /**
     * Message.
     *
     * @return the string
     */
    String message() default Constants.CONST_STR_BLANK;

    /**
     * Pattern.
     *
     * @return the string
     */
    String pattern() default Constants.CONST_STR_BLANK;

    /**
     * Groups.
     *
     * @return the class[]
     */
    Class<?>[] groups() default {};

    /**
     * Payload.
     *
     * @return the class<? extends payload>[]
     */
    Class<? extends Payload>[] payload() default {};

    /**
     * Defines several {@link Pattern} annotations on the same element.
     *
     * @see javax.validation.constraints.Pattern
     */
    @Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
    @Retention(RUNTIME)
    @Documented
    @interface List {

        /**
         * Value.
         *
         * @return the pattern[]
         */
        ValidDate[] value();
    }

}

