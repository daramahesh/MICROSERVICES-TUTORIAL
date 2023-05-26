package org.wanheda.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.wanheda.entities.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
