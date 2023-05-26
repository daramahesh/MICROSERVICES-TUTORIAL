package org.wanheda.service;

import org.wanheda.dto.OrderRequestDto;

public interface OrderService {

    void placeOrder(OrderRequestDto orderRequestDto);

}
