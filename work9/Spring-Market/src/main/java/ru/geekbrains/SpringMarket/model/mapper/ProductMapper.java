package ru.geekbrains.SpringMarket.model.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.geekbrains.SpringMarket.model.Product;
import ru.geekbrains.SpringMarket.model.dto.ProductDTO;

import java.util.List;

@Mapper
public interface ProductMapper {
    ProductMapper MAPPER = Mappers.getMapper(ProductMapper.class);

    Product toProduct(ProductDTO productDTO);

    @InheritInverseConfiguration
    ProductDTO fromProduct(Product product);

    List<Product> toProductList(List<ProductDTO> productDTOList);

    List<ProductDTO> fromProductList(List<Product> productList);
}
