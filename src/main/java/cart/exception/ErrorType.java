package cart.exception;

public enum ErrorType {

    INVALID_MONEY("0 이상의 값이 필요합니다."),
    INVALID_PRODUCT_NAME("잘못된 상품명입니다."),
    INVALID_PRODUCT_IMAGE("잘못된 상품 이미지입니다."),
    INVALID_PRODUCT_PRICE("잘못된 상품 가격입니다."),
    PRODUCT_NOT_FOUND("상품을 찾을 수 없습니다.");

    private final String message;

    ErrorType(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
