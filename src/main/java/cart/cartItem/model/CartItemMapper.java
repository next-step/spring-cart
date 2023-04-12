package cart.cartItem.model;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CartItemMapper {

    CartItemMapper INSTANCE = Mappers.getMapper(CartItemMapper.class);


    @Mapping(source = "productEntity.name", target = "product.name")
    @Mapping(source = "productEntity.image", target = "product.image")
    @Mapping(source = "productEntity.price", target = "product.price")
    CartItem toDto(CartItemEntity src);

}
