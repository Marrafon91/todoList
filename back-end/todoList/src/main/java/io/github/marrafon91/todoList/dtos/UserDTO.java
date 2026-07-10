package io.github.marrafon91.todoList.dtos;

import io.github.marrafon91.todoList.entities.User;

public record UserDTO(
        Long id,
        String name,
        String email

) {

    public UserDTO(User entity) {
        this(
                entity.getId(),
                entity.getName(),
                entity.getEmail()
        );
    }
}
