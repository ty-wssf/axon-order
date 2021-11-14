package com.rickie.order.example.domain.events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderShippedEvent {
    @TargetAggregateIdentifier
    private String orderId;
}
