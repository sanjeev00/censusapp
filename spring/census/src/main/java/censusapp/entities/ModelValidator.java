package censusapp.entities;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.ReflectionUtils;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ ElementType.TYPE, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = ModelValidator.class)
@Documented
/**
 * Annotation to allow custom validation against model classes
 */
@interface Validate {

    /**
     * Validation message
     */
    String message();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    /**
     * Validation method name
     */
    String method() default "";
}




public class ModelValidator implements ConstraintValidator<Validate, Member> {
    private static Log log = LogFactory.getLog(ModelValidator.class);


    @Override
    public void initialize(Validate constraintAnnotation) {
    }

    private boolean isValidForMethod(Member member) throws Exception {
        String name = member.getFirstName()+member.getMiddleName() + member.getLastName();
        if(name.length()>32)
        {
            return false;
        }
        else
            return true;
    }

    @Override
    public boolean isValid(Member member, ConstraintValidatorContext constraintContext) {
        try {
            return isValidForMethod(member);
        } catch (Exception e) {
            log.error("Error validating "+member, e);
            return false;
        }
    }



}
