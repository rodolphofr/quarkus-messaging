package me.rrodrigues;

import java.util.Collection;

import jakarta.validation.constraints.NotEmpty;
import me.rrodrigues.messaging.common.Item;

public record Request(@NotEmpty(message = "Please choose your delicious burger.") Collection<Item> items) {
}
