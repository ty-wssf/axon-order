package com.rickie.order.example.interfaces.events;

import com.rickie.order.example.domain.events.OrderConfirmedEvent;
import com.rickie.order.example.domain.events.OrderPlacedEvent;
import com.rickie.order.example.domain.events.OrderShippedEvent;
import org.axonframework.eventhandling.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.invoke.MethodHandles;

@Component
public class OrderEventHandler {
    private final static Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @EventHandler
    public void orderPlacedEventHandler(OrderPlacedEvent orderPlacedEvent) {
        logger.info("事件处理程序 OrderPlacedEvent " + orderPlacedEvent);
    }

    @EventHandler
    public void orderConfirmedEventHandler(OrderConfirmedEvent orderConfirmedEvent) {
        logger.info("事件处理程序 OrderConfirmedEvent " + orderConfirmedEvent);
    }

    @EventHandler
    public void orderShippedEventHandler(OrderShippedEvent orderShippedEvent) {
        logger.info("事件处理程序 OrderShippedEvent " + orderShippedEvent);
    }
}
