package trainingweb.exceptions;

public class LengthExceededException extends  Exception{
    public LengthExceededException() {
        super("Length  cannot exceed 32 characters");
    }

    public LengthExceededException(String message) {
        super(message);
    }
}
