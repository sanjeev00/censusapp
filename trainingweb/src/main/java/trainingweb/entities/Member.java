package trainingweb.entities;


import trainingweb.exceptions.*;
import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


@Entity
@Table(name = "member")
public class Member {

    @Column(name = "first_name")
    private String firstName;
    @Column(name = "middle_name")
    private String middleName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "date_of_birth")
    private Date dateOfBirth;
    @Column(name = "gender")
    private String gender;
    @Id
    @Column(name = "member_id")
    private String memberId;
    @Column(name = "is_head_member")
    private Boolean isHead;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "application_id", referencedColumnName = "application_id")
    private Application application;

    //private String relationWithHead;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) throws Exception {
        if (firstName.equals("")) {
            throw new EmptyFieldException();
        }
        if(firstName.length()>32)
        {
            throw new LengthExceededException();
        }
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) throws  Exception{
        if(middleName.length()>32)
        {
            throw new LengthExceededException();
        }
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) throws Exception {
        if (lastName == "") {
            throw new EmptyFieldException();
        }
        if(lastName.length()>32)
        {
            throw new LengthExceededException();
        }
        this.lastName = lastName;
    }

    public String getDateOfBirth() {
        return dateOfBirth.toString();
    }

    public void setDateOfBirth(String dateOfBirth) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            this.dateOfBirth =  sdf.parse(dateOfBirth);;
            System.out.println(this.dateOfBirth.toString());
            System.out.println(getAge());

            if(getAge()<0)
                throw new NegativeAgeException();
            if(getAge()>125)
                throw new MaxAgeExceededException();


        } catch (ParseException e) {
            throw new InvalidDateException();
        }
    }
    public int getAge()
    {
        Calendar a = Calendar.getInstance();
        Calendar b = Calendar.getInstance();
        a.setTime(dateOfBirth);
        int diff = b.get(Calendar.YEAR) - a.get(Calendar.YEAR);
        if (a.get(Calendar.MONTH) > b.get(Calendar.MONTH) ||
                (a.get(Calendar.MONTH) == b.get(Calendar.MONTH) && a.get(Calendar.DATE) > b.get(Calendar.DATE))) {
            diff--;
        }
        return diff;
    }




    public String getGender() {
        return gender;
    }

    public void setGender(String gender) throws Exception {
        if (gender == "") {
            throw new EmptyFieldException();
        }
        this.gender = gender;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public Boolean getIsHead() {
        return isHead;
    }

    public void setIsHead(Boolean head) {
        isHead = head;
    }

    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }
}
