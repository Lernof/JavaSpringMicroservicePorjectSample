package ru.javaspring.micro.server.orderservice.models;

import lombok.Data;

@Data
public class User {
    private long id;
    private String username;
    private String email;
}
