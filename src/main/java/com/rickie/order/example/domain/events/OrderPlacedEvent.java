package com.rickie.order.example.domain.events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
public class OrderPlacedEvent {
    @TargetAggregateIdentifier
    private final String orderId;
    private final String product;
}
