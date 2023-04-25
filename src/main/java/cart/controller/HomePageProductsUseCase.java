package cart.controller;

import cart.dto.HomePageProduct;

import java.util.List;

public interface HomePageProductsUseCase {
    List<HomePageProduct> getHomePageProducts();
}
