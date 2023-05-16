package cart.exceptions;

import cart.enums.exceptions.ErrorCode;

public class MemberNotFoundException extends BadRequestException {
    public MemberNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
