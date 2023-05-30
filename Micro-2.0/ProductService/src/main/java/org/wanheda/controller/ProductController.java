package org.wanheda.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.wanheda.dto.ProductRequestDto;
import org.wanheda.dto.ProductResponseDto;
import org.wanheda.entities.Product;
import org.wanheda.service.ProductService;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody ProductRequestDto productRequestDto){

        productService.createProduct(productRequestDto);
    }

    @GetMapping
    public List<ProductResponseDto> getAll() {
       return productService.getAll();
    }

    @GetMapping("/{pid}")
    public Product getById(@PathVariable("pid") long pid) {
        return productService.getById(pid);
    }
}
