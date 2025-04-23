package ru.javaspring.micro.server.orderservice.dto;

import lombok.Data;

@Data
public class UserDTO {
    private long id;
    private String username;
    private String email;
}
