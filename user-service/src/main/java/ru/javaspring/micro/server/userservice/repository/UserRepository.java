package ru.javaspring.micro.server.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.javaspring.micro.server.userservice.models.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
