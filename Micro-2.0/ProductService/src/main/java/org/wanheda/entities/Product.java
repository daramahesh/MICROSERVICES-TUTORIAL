package org.wanheda.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Service;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "micro_product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long pid;
    private String name;
    private long price;

}
