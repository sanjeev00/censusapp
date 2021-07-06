package censusapp.entities;

import censusapp.exceptions.InvalidMemberException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

public class MemberValidator
{
    public Member member;
    private List<ObjectError> errors;
    public MemberValidator(Member member)
    {
        this.member = member;
        errors = new ArrayList<>();

    }


    public boolean checkName()
    {
        String name =  member.getFirstName()+member.getLastName()+member.getMiddleName();
        return name.length()<=32;
    }


    public boolean checkAge()
    {
       return  member.getAge()<125;
    }



    public boolean validate() throws Exception
    {
        if(!checkName()) {
        errors.add(new FieldError("member","member","Name cannot be more than 32 characters"));
        throw new InvalidMemberException(errors);
        }
        return true;
    }

}


