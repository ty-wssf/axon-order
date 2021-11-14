package com.rickie.order.example.domain.commands;

import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

/**
 * 下新订单
 */
@Data
public class ShipOrderCommand {
    @TargetAggregateIdentifier
    private final String orderId;
}
