package censusapp.exceptions;

import javassist.expr.Instanceof;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.validation.ConstraintViolationException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionAdvice {


    /*
        validate before controller
     */
    @ResponseBody
    @ExceptionHandler(InvalidMemberException.class)
    public ResponseEntity<Map<String,String>> validationExceptions(InvalidMemberException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getErrors().forEach((error) -> {
            String fieldName;
            if(error instanceof FieldError)
                fieldName = ((FieldError)error).getField();
            else
                fieldName = error.getObjectName();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
    }

    /*
    validate before persist
     */
    @ResponseBody
    @ExceptionHandler(TransactionSystemException.class)
    public ResponseEntity<Map<String,String>> validationExceptions(Exception exc) {
        Throwable cause = ((TransactionSystemException) exc).getRootCause();
        Map<String, String> errors = new HashMap<>();
        ((ConstraintViolationException)cause).getConstraintViolations().forEach((error)->{
            errors.put(error.getPropertyPath().toString(),error.getMessage());
        });


        return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
    }


    @ResponseBody
    @ExceptionHandler(MemberNotFoundException.class)
    ResponseEntity<String> handleMemberNotFound(MemberNotFoundException e)
    {
        return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
    }
}
