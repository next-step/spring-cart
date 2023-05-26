package cart.controller.dto.response;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class CartsResponse {

    private List<CartResponse> cartsResponses;

    public static CartsResponse of (List<CartResponse> cartResponses) {
        return new CartsResponse(cartResponses);
    }
}
