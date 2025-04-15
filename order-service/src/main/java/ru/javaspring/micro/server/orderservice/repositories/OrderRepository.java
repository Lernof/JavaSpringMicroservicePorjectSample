package ru.javaspring.micro.server.orderservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.javaspring.micro.server.orderservice.models.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
