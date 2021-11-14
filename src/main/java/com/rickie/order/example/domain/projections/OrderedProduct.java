package com.rickie.order.example.domain.projections;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="ordered_product")
@Data
public class OrderedProduct {
    @Id
    private String orderId;
    private String product;
    private OrderStatus orderStatus;

    public OrderedProduct(String orderId, String product) {
        this.orderId = orderId;
        this.product = product;
        orderStatus = OrderStatus.PLACED;
    }

    public OrderedProduct() {
        orderStatus = OrderStatus.PLACED;
    }

    public void setOrderConfirmed() {
        orderStatus = OrderStatus.CONFIRMED;
    }

    public void setOrderShipped() {
        orderStatus = OrderStatus.SHIPPED;
    }
}
