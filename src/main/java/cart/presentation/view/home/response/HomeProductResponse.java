package cart.presentation.view.home.response;

import cart.domain.product.model.ProductModel;

import java.util.List;
import java.util.stream.Collectors;

public class HomeProductResponse {

    private final Long id;
    private final String name;
    private final String imageUrl;
    private final Long price;

    public HomeProductResponse(Long id, String name, String imageUrl, Long price) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.price = price;
    }

    public static List<HomeProductResponse> from(List<ProductModel> productModels) {
        return productModels.stream()
            .map(productModel -> new HomeProductResponse(productModel.getId(), productModel.getName(), productModel.getImageUrl(), productModel.getPrice()))
            .collect(Collectors.toList());
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Long getPrice() {
        return price;
    }
}
