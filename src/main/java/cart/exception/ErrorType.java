package cart.exception;

public enum ErrorType {

    INVALID_MONEY("0 이상의 값이 필요합니다."),
    INVALID_PRODUCT_NAME("잘못된 상품명입니다."),
    INVALID_PRODUCT_IMAGE("잘못된 상품 이미지입니다."),
    INVALID_PRODUCT_PRICE("잘못된 상품 가격입니다."),
    PRODUCT_NOT_FOUND("상품을 찾을 수 없습니다."),
    INVALID_CART_MEMBER_ID("잘못된 회원 ID입니다."),
    INVALID_CART_QUANTITY("잘못된 장바구니 수량입니다."),
    AUTHENTICATION_FAILED("인증에 실패했습니다."),
    NOT_FOUND_MEMBER("회원을 찾을 수 없습니다.");

    private final String message;

    ErrorType(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
