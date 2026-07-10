package io.github.marrafon91.todoList.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserUpdateDTO(

        @NotBlank(message = "O nome é obrigatório")
        @Size(min = 3, max = 50)
        String name,

        @NotBlank(message = "O e-mail é obrigatório")
        @Email(message = "Informe um e-mail válido")
        String email
) {
}
