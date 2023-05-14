package cart.presentation.dto;

public class ViewCartItem {

    private Long id;
    private String imageUrl;
    private String name;
    private Integer price;

    public ViewCartItem(Long id, String imageUrl, String name, Integer price) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.name = name;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getName() {
        return name;
    }

    public Integer getPrice() {
        return price;
    }
}
