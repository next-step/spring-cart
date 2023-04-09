package cart.product.dto;

public class ProductCartDto {

    private Integer id;
    private String productName;
    private String imageUrl;

    private Integer price;

    public ProductCartDto() {
    }

    public ProductCartDto(Integer id, String productName, String imageUrl, Integer price) {
        this.id = id;
        this.productName = productName;
        this.imageUrl = imageUrl;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public String getProductName() {
        return productName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Integer getPrice() {
        return price;
    }
}
