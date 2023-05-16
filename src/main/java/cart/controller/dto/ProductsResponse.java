package cart.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ProductsResponse {

    private List<ProductResponse> productResponses;

    public static ProductsResponse of (List<ProductResponse> productResponses) {
        return new ProductsResponse(productResponses);
    }
}
