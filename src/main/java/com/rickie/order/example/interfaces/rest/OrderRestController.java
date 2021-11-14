package com.rickie.order.example.interfaces.rest;

import com.rickie.order.example.domain.commands.ConfirmOrderCommand;
import com.rickie.order.example.domain.commands.PlaceOrderCommand;
import com.rickie.order.example.domain.commands.ShipOrderCommand;
import com.rickie.order.example.domain.projections.OrderedProduct;
import com.rickie.order.example.domain.queries.FindAllOrderedProductsQuery;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
public class OrderRestController {
    private final CommandGateway commandGateway;
    private final QueryGateway queryGateway;

    public OrderRestController(CommandGateway commandGateway, QueryGateway queryGateway) {
        this.commandGateway = commandGateway;
        this.queryGateway = queryGateway;
    }

    @GetMapping("/ship-order")
    public String shipOrder() throws InterruptedException {
        String orderId = UUID.randomUUID().toString();
        commandGateway.send(new PlaceOrderCommand(orderId, "Axon框架快速入门和DDD项目实践"));
        commandGateway.send(new PlaceOrderCommand(orderId, "Axon框架快速入门和DDD项目实践"));
        commandGateway.send(new PlaceOrderCommand(orderId, "Axon框架快速入门和DDD项目实践"));
        TimeUnit.SECONDS.sleep(2);
        commandGateway.send(new ConfirmOrderCommand(orderId));
        TimeUnit.SECONDS.sleep(2);
        commandGateway.send(new ShipOrderCommand(orderId));
        return orderId;
    }

    @GetMapping("all-orders")
    public List<OrderedProduct> findAllOrderedProducts(){
        return queryGateway.query(
                new FindAllOrderedProductsQuery(),
                ResponseTypes.multipleInstancesOf(OrderedProduct.class)
        ).join();
    }
}
