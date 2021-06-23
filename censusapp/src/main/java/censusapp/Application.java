package censusapp;

import java.util.HashMap;

public class Application {
    private String status;
    private String applicationId;
    private HashMap<String,Member> members;


    public Application(String status,String applicationId) {
        this.status = status;
        this.applicationId =applicationId;
    }


    public HashMap<String, Member> getMembers()
    {
        if(members.isEmpty())
        {
            //Execute sql to populate members
        }
        return members;
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
