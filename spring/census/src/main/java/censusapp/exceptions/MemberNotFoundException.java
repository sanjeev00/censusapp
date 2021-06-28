package censusapp.exceptions;

public class MemberNotFoundException extends Exception {
    public MemberNotFoundException() {
        super("member not found");
    }
}
