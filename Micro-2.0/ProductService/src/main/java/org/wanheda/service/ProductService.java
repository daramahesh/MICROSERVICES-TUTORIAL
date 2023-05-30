package org.wanheda.service;

import org.springframework.stereotype.Service;
import org.wanheda.dto.ProductRequestDto;
import org.wanheda.dto.ProductResponseDto;
import org.wanheda.entities.Product;

import java.util.List;

public interface ProductService {
    void createProduct(ProductRequestDto productRequestDto);
    List<ProductResponseDto> getAll();
    Product getById(long pid);
}
