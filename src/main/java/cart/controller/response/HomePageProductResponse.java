package cart.controller.response;

import cart.dto.ProductDto;

import java.util.List;
import java.util.stream.Collectors;

public class HomePageProductResponse {
    private final Long id;
    private final String name;
    private final String image;
    private final Long price;

    public HomePageProductResponse(Long id, String name, String image, Long price) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.price = price;
    }

    public static List<HomePageProductResponse> ofProductDtos(List<ProductDto> productDtos) {
        return productDtos
                .stream()
                .map(HomePageProductResponse::fromProductDto)
                .collect(Collectors.toList());
    }

    private static HomePageProductResponse fromProductDto(ProductDto productDto) {
        return new HomePageProductResponse(
                productDto.getId(),
                productDto.getName(),
                productDto.getImage(),
                productDto.getPrice()
        );
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public Long getPrice() {
        return price;
    }
}
