package org.wanheda.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.wanheda.dto.OrderRequestDto;
import org.wanheda.entities.Order;
import org.wanheda.entities.Product;
import org.wanheda.repository.OrderRepository;
import org.wanheda.service.OrderService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private WebClient webClient;
    @Override
    public void placeOrder(OrderRequestDto orderRequestDto) {

        Product[] products = webClient.get().uri("http://PRODUCT-SERVICE/api/products").retrieve().bodyToMono(Product[].class).block();
        assert products != null;
        List<Product> productList = Arrays.asList(products);

        List<Order>list=new ArrayList<>();

        for (Product p : productList){

            if(p.getName().equals(orderRequestDto.getOrderDetails())&&p.getQuantity()>0){

                Order order = new Order();
                order.setOrderDetails(p.getName());
                order.setPid(p.getPid());
                list.add(order);
            }
        }
        if(list.isEmpty()){
            throw  new NullPointerException("out of stock");
        }
        else{
            this.orderRepository.saveAll(list);
            for(Product pp: productList){
                Object block = webClient.put().uri("http://PRODUCT-SERVICE/api/products/update/" + pp.getPid()).retrieve().bodyToMono(Object.class).block();
            }

        }
    }
}
