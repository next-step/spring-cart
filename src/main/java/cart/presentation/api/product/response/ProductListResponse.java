package cart.presentation.api.product.response;

import java.util.List;

public class ProductListResponse {

    private final List<ProductDetailResponse> products;

    public ProductListResponse(List<ProductDetailResponse> products) {
        this.products = products;
    }

    public static class ProductDetailResponse {
        private final Long id;
        private final String name;
        private final String imageUrl;
        private final Long price;

        public ProductDetailResponse(Long id, String name, String imageUrl, Long price) {
            this.id = id;
            this.name = name;
            this.imageUrl = imageUrl;
            this.price = price;
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

    public List<ProductDetailResponse> getProducts() {
        return products;
    }
}
