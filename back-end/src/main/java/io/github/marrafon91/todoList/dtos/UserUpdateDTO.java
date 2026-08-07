package io.github.marrafon91.todoList.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserUpdateDTO(

        @Schema(
                description = "Nome do usuário.",
                example = "Maria Oliveira"
        )
        @NotBlank(message = "O nome é obrigatório")
        @Size(min = 3, max = 50)
        String name,

        @Schema(
                description = "E-mail do usuário.",
                example = "maria@example.com"
        )
        @NotBlank(message = "O e-mail é obrigatório")
        @Email(message = "Informe um e-mail válido")
        String email
) {
}
