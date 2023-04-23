package cart.exception;

public class MemberException extends IllegalArgumentException {

    public MemberException() {
        super("Member exception");
    }

    public MemberException(String msg) {
        super(msg);
    }
}
