package io.github.marrafon91.todoList.dtos;

import io.github.marrafon91.todoList.entities.User;
import io.swagger.v3.oas.annotations.media.Schema;

public record UserDTO(

        @Schema(
                description = "Identificador único do usuário.",
                example = "1"
        )
        Long id,

        @Schema(
                description = "Nome do usuário.",
                example = "Guilherme Marrafon"
        )
        String name,

        @Schema(
                description = "E-mail do usuário.",
                example = "guilherme@example.com"
        )
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
