package ru.javaspring.micro.server.orderservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javaspring.micro.server.orderservice.clients.UserClient;
import ru.javaspring.micro.server.orderservice.models.Order;
import ru.javaspring.micro.server.orderservice.models.User;
import ru.javaspring.micro.server.orderservice.repositories.OrderRepository;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    private final UserClient userClient;

    @Autowired
    public OrderService(OrderRepository orderRepository, UserClient userClient) {
        this.orderRepository = orderRepository;
        this.userClient = userClient;
    }

    public Order createOrder(Order order) {
        User user = userClient.getUserById(order.getUserId());
        if (user == null) {
            throw new RuntimeException("User not found with id: " + order.getUserId());
        }
        return orderRepository.save(order);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    public Order updateOrder(Long id, Order orderDetails) {
        Optional<Order> optionalOrder = orderRepository.findById(id);
        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            order.setProduct(orderDetails.getProduct());
            order.setPrice(orderDetails.getPrice());
            order.setUserId(orderDetails.getUserId());
            User user = userClient.getUserById(order.getUserId());
            if (user == null) {
                throw new RuntimeException("User not found with id: " + order.getUserId());
            }
            return orderRepository.save(order);
        }
        throw new RuntimeException("Order not found with id: " + id);
    }

    public void deleteOrder(Long id) {
        if (orderRepository.existsById(id)) {
            orderRepository.deleteById(id);
        } else {
            throw new RuntimeException("Order not found with id: " + id);
        }
    }

    public User getUserForOrder(Long orderId) {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            return userClient.getUserById(order.getUserId());
        }
        throw new RuntimeException("Order not found with id: " + orderId);
    }
}
