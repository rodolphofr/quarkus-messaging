package me.rrodrigues;

import java.time.ZonedDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

public record OrderResponse(
    @JsonProperty("order") String orderId, 
    @JsonProperty("create_at") ZonedDateTime createAt) {
}
