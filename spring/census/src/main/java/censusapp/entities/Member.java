package censusapp.entities;

import  censusapp.exceptions.*;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.time.DurationMax;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.UUID;


@Entity
@Table(name = "member")
public class Member {
    @NotBlank(message="First Name cannot be empty")
    @Column(name = "first_name")
    private String firstName;

    @Column(name = "middle_name")
    private String middleName;

    @NotBlank(message="Last Name cannot be empty")
    @Column(name = "last_name")
    private String lastName;

    @NotNull(message="Date of Birth cannot be empty")
    @Past(message="Invalid Date")
    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    @NotBlank(message="Gender cannot be empty")
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

    @PrePersist
    private void ensureMemberId(){
        this.setMemberId(String.valueOf((new Random()).nextLong()));
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) throws Exception {

        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) throws  Exception{

        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) throws Exception {

        this.lastName = lastName;
    }

    public String getDateOfBirth() {
        return dateOfBirth.toString();
    }

    public void setDateOfBirth(String dateOfBirth) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            this.dateOfBirth =  sdf.parse(dateOfBirth);

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
