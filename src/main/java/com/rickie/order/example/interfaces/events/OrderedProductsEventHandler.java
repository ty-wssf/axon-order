package com.rickie.order.example.interfaces.events;

import com.rickie.order.example.application.internal.OrderedProductService;
import com.rickie.order.example.domain.events.OrderConfirmedEvent;
import com.rickie.order.example.domain.events.OrderPlacedEvent;
import com.rickie.order.example.domain.events.OrderShippedEvent;
import com.rickie.order.example.domain.projections.OrderedProduct;
import com.rickie.order.example.domain.queries.FindAllOrderedProductsQuery;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderedProductsEventHandler {
    private final static Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    // 已订购产品维护在orderedProducts中
    private final Map<String, OrderedProduct> orderedProducts = new HashMap<>();
    private final OrderedProductService orderedProductService;

    public OrderedProductsEventHandler(OrderedProductService orderedProductService){
        this.orderedProductService = orderedProductService;
    }

    @EventHandler
    public void on(OrderPlacedEvent event) {
        logger.info("OrderPlacedEvent Handler orderId: " + event.getOrderId());
        String orderId = event.getOrderId();
        OrderedProduct orderedProduct = new OrderedProduct(orderId, event.getProduct());
        orderedProducts.put(orderId, orderedProduct);
        orderedProductService.save(orderedProduct);
    }

    @EventHandler
    public void on(OrderConfirmedEvent event) {
        logger.info("OrderConfirmedEvent Handler orderId: " + event.getOrderId());
        // 如果orderId存在，更新orderStatus状态
        orderedProducts.computeIfPresent(
                event.getOrderId(),
                (orderId, orderedProduct) -> {
                    orderedProduct.setOrderConfirmed();
                    return orderedProduct;
                }
        );
        orderedProductService.save(orderedProducts.get(event.getOrderId()));
    }

    @EventHandler
    public void on(OrderShippedEvent event) {
        logger.info("OrderShippedEvent Handler orderId: " + event.getOrderId());
        orderedProducts.computeIfPresent(
                event.getOrderId(),
                (orderId, orderedProduct) -> {
                    orderedProduct.setOrderShipped();
                    return orderedProduct;
                }
        );
        orderedProductService.save(orderedProducts.get(event.getOrderId()));
    }

    @QueryHandler
    public List<OrderedProduct> handle(FindAllOrderedProductsQuery query) {
        return new ArrayList<>(orderedProducts.values());
    }
}
