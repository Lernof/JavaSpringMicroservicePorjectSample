package ru.javaspring.micro.server.orderservice.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.javaspring.micro.server.orderservice.dto.UserDTO;

@FeignClient(name = "user-service")
public interface UserClient {
    @GetMapping("/users/{id}")
    UserDTO getUserById(@PathVariable(name="id") Long id);
}
