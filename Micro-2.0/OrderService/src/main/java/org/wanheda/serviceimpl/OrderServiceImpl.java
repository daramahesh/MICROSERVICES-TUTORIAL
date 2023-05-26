package org.wanheda.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wanheda.dto.OrderLineItemsDto;
import org.wanheda.dto.OrderRequestDto;
import org.wanheda.entities.Order;
import org.wanheda.entities.OrderLineItems;
import org.wanheda.repository.OrderRepository;
import org.wanheda.service.OrderService;

import java.util.List;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;
    Order order = new Order();
    @Override
    public void placeOrder(OrderRequestDto orderRequestDto) {

        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLineItems> orderLineItems = orderRequestDto
                                              .getOrderLineItemsDto()
                                              .stream().map(this::mapToDto).toList();
        order.setOrderLineItems(orderLineItems);
        this.orderRepository.save(order);
    }

    private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto) {
        OrderLineItems orderLineItems = new OrderLineItems();

        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
        orderLineItems.setPrice(orderLineItemsDto.getPrice());
        orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
        orderLineItems.setOrder(order);
        return orderLineItems;
    }
}
