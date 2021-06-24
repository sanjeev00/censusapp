package trainingweb.exceptions;

public class MaxAgeExceededException extends  Exception{
    public MaxAgeExceededException() {
        super("Age Cannot be more than 125");
    }
}
