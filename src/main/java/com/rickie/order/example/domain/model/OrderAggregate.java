package com.rickie.order.example.domain.model;

import com.rickie.order.example.domain.commands.ConfirmOrderCommand;
import com.rickie.order.example.domain.commands.PlaceOrderCommand;
import com.rickie.order.example.domain.commands.ShipOrderCommand;
import com.rickie.order.example.domain.events.OrderConfirmedEvent;
import com.rickie.order.example.domain.events.OrderPlacedEvent;
import com.rickie.order.example.domain.events.OrderShippedEvent;
import com.rickie.order.example.domain.exceptions.UnconfirmedOrderException;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;

@Aggregate
public class OrderAggregate {
    private final static Logger logger = LoggerFactory.getLogger(
            MethodHandles.lookup().lookupClass());

    @AggregateIdentifier
    private String orderId;
    private boolean orderConfirmed;

    @CommandHandler
    public OrderAggregate(PlaceOrderCommand command) {
        logger.info("命令处理程序 " + command);
        orderId = command.getOrderId();
        AggregateLifecycle.apply(new OrderPlacedEvent(command.getOrderId(), command.getProduct()));
    }

    @CommandHandler
    public void handle(ConfirmOrderCommand command) {
        logger.info("命令处理程序 " + command);
        orderId = command.getOrderId();
        AggregateLifecycle.apply(new OrderConfirmedEvent(orderId));
    }

    @CommandHandler
    public void handle(ShipOrderCommand command) {
        logger.info("命令处理程序 " + command);
        orderId = command.getOrderId();
        if(!orderConfirmed) {
            throw new UnconfirmedOrderException();
        }
        AggregateLifecycle.apply(new OrderShippedEvent(orderId));
    }

    @EventSourcingHandler
    public void on(OrderPlacedEvent event) {
        logger.info("事件溯源处理程序 " + event);
        this.orderId = event.getOrderId();
        orderConfirmed = false;
    }

    @EventSourcingHandler
    public void on(OrderConfirmedEvent event) {
        logger.info("事件溯源处理程序 " + event);
        this.orderConfirmed = true;
    }

    protected OrderAggregate() {
        // Required by Axon to build a default Aggregate prior to Event Sourcing
    }
}
