package cart.enums.exceptions;

public enum ValidCode {
    NOT_NULL(400, "230510"),
    NOT_BLANK(400,"230511"),
    PATTERN(400, "230512"),
    MIN_VALUE(400, "230513"),
    MAX_VALUE(400, "230514")
    ;

    private final int status;
    private final String code;

    ValidCode(int status, String code) {
        this.status = status;
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public int getStatus() {
        return status;
    }
}
