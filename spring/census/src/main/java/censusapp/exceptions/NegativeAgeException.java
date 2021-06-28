package censusapp.exceptions;

public class NegativeAgeException extends  Exception{
    public NegativeAgeException() {
        super("Age Cannot be negative");
    }
}
