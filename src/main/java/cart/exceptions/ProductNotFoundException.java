package cart.exceptions;

import cart.enums.exceptions.ErrorCode;

public class ProductNotFoundException extends BadRequestException {
    public ProductNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
