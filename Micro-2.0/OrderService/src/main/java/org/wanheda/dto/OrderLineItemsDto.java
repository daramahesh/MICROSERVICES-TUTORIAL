package org.wanheda.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderLineItemsDto {
    private Long id;
    private Long price;
    private String skuCode;
    private Integer quantity;
}
