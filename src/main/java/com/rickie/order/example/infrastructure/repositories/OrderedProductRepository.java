package com.rickie.order.example.infrastructure.repositories;

import com.rickie.order.example.domain.projections.OrderedProduct;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderedProductRepository extends CrudRepository<OrderedProduct, String> {
}
