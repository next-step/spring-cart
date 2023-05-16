package cart.enums.exceptions;

import lombok.Getter;

@Getter
public enum ErrorCode {
    NOT_FOUND_PRODUCT(404, "PRODUCT-ERR", "해당 상품은 존재하지 않습니다."),
    NOT_FOUND_MEMBER(404, "MEMBER-ERR", "사용자는 존재하지 않습니다.")
    ;

    private final int status;
    private final String errorCode;
    private final String message;

    ErrorCode(int status, String errorCode, String message) {
        this.status = status;
        this.errorCode = errorCode;
        this.message = message;
    }
}
