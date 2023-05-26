package org.wanheda.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "micro_orderLineItems")
public class OrderLineItems {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long price;
    private String skuCode;
    private Integer quantity;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
}
