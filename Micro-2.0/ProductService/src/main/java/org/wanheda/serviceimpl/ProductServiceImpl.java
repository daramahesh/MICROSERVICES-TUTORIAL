package org.wanheda.serviceimpl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wanheda.dto.ProductRequestDto;
import org.wanheda.dto.ProductResponseDto;
import org.wanheda.entities.Product;
import org.wanheda.repository.ProductRepository;
import org.wanheda.service.ProductService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Override
    public void createProduct(ProductRequestDto productRequestDto) {

        Product product = Product.builder()
                .name(productRequestDto.getName())
                .price(productRequestDto.getPrice())
                .build();
        productRepository.save(product);
        log.info("product {} is saved", product.getPid());
    }

    @Override
    public List<ProductResponseDto> getAll() {
        List<Product>products = productRepository.findAll();
        return products.stream().map(this::mapToProductResponse).toList();
    }

    @Override
    public Product getById(long pid) {
        return productRepository.findById(String.valueOf(pid)).orElseThrow(()->new NullPointerException());
    }

    private ProductResponseDto mapToProductResponse(Product product) {
        return ProductResponseDto.builder()
                .pid(product.getPid())
                .name(product.getName())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .build();
    }
}
