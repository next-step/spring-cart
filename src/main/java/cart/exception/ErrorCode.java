package cart.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "내부 서버 오류가 발생했습니다."),
    PRODUCT_NOT_FOUND(HttpStatus.NOT_FOUND, "상품이 존재하지 않습니다."),
    NOT_VALID_PATH(HttpStatus.BAD_REQUEST, "요청하려는 path가 잘못되었습니다."),
    DUPLICATED_USER_EMAIL(HttpStatus.CONFLICT, "가입하려는 email이 이미 존재합니다."),
    USER_NOT_FOUND(HttpStatus.UNAUTHORIZED, "회원이 존재하지 않습니다."),
    INVALID_PASSWORD_INFORMATION(HttpStatus.BAD_REQUEST, "비밀번호가 일치하지 않습니다"),
    INVALID_AUTHORIZATION(HttpStatus.UNAUTHORIZED, "유효하지 않은 토큰입니다."),
    CART_NOT_FOUND(HttpStatus.NOT_FOUND, "장바구니가 존재하지 않습니다."),
    UNAUTHORIZED_CART(HttpStatus.UNAUTHORIZED, "권한이 없는 장바구니입니다.")
    ;


    private HttpStatus status;
    private String message;
}
