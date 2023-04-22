package cart.exception;

public class MemberException extends IllegalArgumentException {

    public MemberException() {
    }

    public MemberException(String msg) {
        super(msg);
    }
}
