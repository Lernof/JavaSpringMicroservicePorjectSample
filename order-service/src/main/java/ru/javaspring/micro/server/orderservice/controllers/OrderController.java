package ru.javaspring.micro.server.orderservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.javaspring.micro.server.orderservice.dto.OrderResponseDTO;
import ru.javaspring.micro.server.orderservice.models.Order;
import ru.javaspring.micro.server.orderservice.dto.UserDTO;
import ru.javaspring.micro.server.orderservice.services.OrderService;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public Order createOrder(@RequestBody Order order) {
        return orderService.createOrder(order);
    }

    @GetMapping
    public List<OrderResponseDTO> getAllOrders() {
        List<Order> myOrders = orderService.getAllOrders();
        return myOrders.stream().map(order -> {
            UserDTO user = orderService.getUserForOrder(order.getId());
            return new OrderResponseDTO(order.getId(), order.getProduct(), order.getPrice(), user);
        }).toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        return orderService.getOrderById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable Long id, @RequestBody Order orderDetails) {
        try {
            Order updatedOrder = orderService.updateOrder(id, orderDetails);
            return ResponseEntity.ok(updatedOrder);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        try {
            orderService.deleteOrder(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/user")
    public ResponseEntity<UserDTO> getUserForOrder(@PathVariable Long id) {
        try {
            UserDTO user = orderService.getUserForOrder(id);
            return ResponseEntity.ok(user);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
