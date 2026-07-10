package io.github.marrafon91.todoList.repositories;

import io.github.marrafon91.todoList.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
