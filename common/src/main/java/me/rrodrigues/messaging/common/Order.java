package me.rrodrigues.messaging.common;

import java.util.Objects;
import java.util.Collection;

public record Order(String orderId, Collection<Item> items) {
    public Order {
        Objects.requireNonNull(orderId, "Order Id cannot be null.");
        Objects.requireNonNull(items, "Items cannot be null.");
    }
}

