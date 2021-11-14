package com.rickie.order.example.domain.commands;

import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

/**
 * 确认订单
 */
@Data
public class ConfirmOrderCommand {
    @TargetAggregateIdentifier
    private final String orderId;
}
