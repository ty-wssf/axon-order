package com.rickie.order.example.domain.model;

import com.rickie.order.example.domain.commands.PlaceOrderCommand;
import com.rickie.order.example.domain.events.OrderPlacedEvent;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class OrderAggregateTest {
    private FixtureConfiguration<OrderAggregate> fixture;

    @BeforeEach
    public void setUp() {
        fixture = new AggregateTestFixture<>(OrderAggregate.class);
    }

    @Test
    public void giveNoPriorActivity_whenPlaceOrderCommand_thenShouldPublishOrderPlacedEvent() {
        String orderId = UUID.randomUUID().toString();
        String product = "DDD领域驱动设计";
        fixture.givenNoPriorActivity()
                .when(new PlaceOrderCommand(orderId, product))
                .expectEvents(new OrderPlacedEvent(orderId, product));
    }
}