package com.rickie.order.example.domain.commands;

import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

/**
 * 发货订单
 */
@Data
public class PlaceOrderCommand {
    @TargetAggregateIdentifier
    private final String orderId;
    private final String product;
}
