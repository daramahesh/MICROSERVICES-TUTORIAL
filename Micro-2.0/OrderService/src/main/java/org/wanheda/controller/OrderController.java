package org.wanheda.controller;

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
    public String placeOrder(@RequestBody OrderRequestDto orderRequestDto) {
        this.orderService.placeOrder(orderRequestDto);
        return "Order placed successfully";
    }
}
