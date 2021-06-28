package censusapp.exceptions;

import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;

import java.util.List;

public class InvalidMemberException extends Exception {
    private List<ObjectError> errors;

    public InvalidMemberException() {
        super("Following field has errors");
    }

    public InvalidMemberException(List<ObjectError> errors)
    {
        super("Following field has errors");
        this.errors =errors;
    }

    public List<ObjectError> getErrors(){return errors;}
}
