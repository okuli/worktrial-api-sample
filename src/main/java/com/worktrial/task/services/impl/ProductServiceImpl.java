package com.worktrial.task.services.impl;

import com.worktrial.task.dtos.ProductDto;
import com.worktrial.task.exceptions.BaseException;
import com.worktrial.task.exceptions.ProductException;
import com.worktrial.task.models.Product;
import com.worktrial.task.repositories.ProductRepository;
import com.worktrial.task.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public Product createProduct(ProductDto productDto) {
        Product product = new Product();
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setLabel(productDto.getLabel());
        return productRepository.save(product);
    }

    @Override
    public List<Product> findProductByIds(List<String> productIds) throws BaseException {
        List<Product> products = productRepository.findAllById(productIds);
        if (products.size() != productIds.size()) {
            Set<String> idSet = products.stream().map(Product::getId).collect(Collectors.toSet());
            productIds.removeIf(idSet::contains);
            throw new ProductException(HttpStatus.BAD_REQUEST, "Product id not found, " + productIds);
        }
        return products;
    }

    @Override
    public List<Product> getAllProducts() throws BaseException {
        List<Product> products = productRepository.findAll();
        if (CollectionUtils.isEmpty(products)) {
            throw new ProductException(ProductException.ProductError.EMPTY_PRODUCT_LIST);
        }
        return products;
    }

    @Override
    public Product findProductById(String productId) throws ProductException {
        return productRepository.findById(productId).orElseThrow(() -> new ProductException(ProductException.ProductError.NOT_FOUND));
    }
}
