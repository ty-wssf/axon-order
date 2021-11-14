package com.rickie.order.example.application.internal;

import com.rickie.order.example.domain.projections.OrderedProduct;
import com.rickie.order.example.infrastructure.repositories.OrderedProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderedProductService {
    @Autowired
    private OrderedProductRepository productRepository;

    public void save(OrderedProduct product) {
        productRepository.save(product);
    }
}
