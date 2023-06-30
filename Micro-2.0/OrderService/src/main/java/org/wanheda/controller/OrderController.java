package org.wanheda.controller;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.wanheda.dto.OrderRequestDto;
import org.wanheda.service.OrderService;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @CircuitBreaker(name = "orderBreaker", fallbackMethod = "temp")
    //@Retry(name = "retry",fallbackMethod = "temp")
    public String placeOrder(@RequestBody OrderRequestDto orderRequestDto) {
        this.orderService.placeOrder(orderRequestDto);
        return "Order placed successfully";
    }

    public String temp(OrderRequestDto orderRequestDto,RuntimeException runtimeException) {
        return "product service is down";
    }

}
