package censusapp.entities;

public class ApplicationBuilder {
    private String status;
    private String applicationId;

    public ApplicationBuilder()
    {

    }

    public ApplicationBuilder setStatus(String status){
        this.status = status;
        return this;
    }
    public  ApplicationBuilder setApplicationId(String applicationId)
    {
        this.applicationId = applicationId;
        return this;
    }

    public Application build()
    {
        Application app = new Application(status,applicationId);
        return app;
    }
}
