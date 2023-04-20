package cart.dto;

import cart.domain.Product;

public class ProductDto {
    private Integer id;
    private String name;
    private String imageUrl;
    private Integer price;
    public ProductDto(){
    }
    public ProductDto(String name, String imageUrl, int price) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.price = price;
    }

    public ProductDto(Integer id, String name, String imageUrl, int price) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Integer getPrice() {
        return price;
    }

    public Product toEntity() {
        if (id == null) {
            new Product(name, imageUrl, price);
        }
        return new Product(id, name, imageUrl, price);
    }
}
