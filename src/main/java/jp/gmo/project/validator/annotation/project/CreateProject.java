/*
 * Aviva SMS Revamp
 * COPYRIGHT(C) GMO-Z.com RUNSYSTEM
 *
 * Author: HungVT
 * Creation Date : Oct 16, 2019
 */
package jp.gmo.project.validator.annotation.project;

import jp.gmo.project.constant.Constants;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * The Interface CreateBlockSMS.
 */
@Documented
@Constraint(validatedBy = {CreateProjectValidator.class})
@Target({TYPE, ANNOTATION_TYPE})
@Retention(RUNTIME)
public @interface CreateProject {

    /**
     * Message.
     *
     * @return the string
     */
    String message() default Constants.CONST_STR_BLANK;

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
     * The Interface List.
     */
    @Target({METHOD, TYPE, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
    @Retention(RUNTIME)
    @Documented
    @interface List {

        /**
         * Value.
         *
         * @return the creates the block SM s[]
         */
        CreateProject[] value();
    }

}
