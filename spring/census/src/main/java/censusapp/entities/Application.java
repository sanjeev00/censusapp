package censusapp.entities;

import censusapp.entities.Member;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.HashMap;

@Entity
@Table(name = "application")
public class Application {
    @Column(name="status")
    private String status;
    @Id
    @Column(name="application_id")
    private String applicationId;
    //private HashMap<String, Member> members;

    public Application()
    {

    }


    public Application(String status,String applicationId) {
        this.status = status;
        this.applicationId =applicationId;
    }




    public void setStatus(String status) {
        this.status = status;
    }

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public String getStatus() {
        return status;
    }

}
