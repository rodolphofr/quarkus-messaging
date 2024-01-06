package me.rrodrigues;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.concurrent.atomic.AtomicInteger;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.jboss.resteasy.reactive.RestResponse.StatusCode;

import jakarta.validation.Valid;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import me.rrodrigues.messaging.common.Order;

@Path("/burgershop")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class BurgerShopResource {
    private final AtomicInteger orderIdGenerator = new AtomicInteger(0);

    @Channel("kitchen")
    Emitter<Order> kitchen;
    
    @POST
    @Path("/request")
    public Response request(@Valid Request req, @HeaderParam("Locale-Zone-Id") String zoneId) {
        if (zoneId == null) {
            throw new BadRequestException("Required Zone Id");
        }

        final var orderId = String.valueOf(orderIdGenerator.incrementAndGet());
        final var createAt = ZonedDateTime.now(ZoneId.of(zoneId));
        final var order = new Order(orderId, req.items());

        kitchen.send(order);

        return Response
                .status(StatusCode.CREATED)
                .entity(new OrderResponse(orderId, createAt))
                .build();
    }
}
