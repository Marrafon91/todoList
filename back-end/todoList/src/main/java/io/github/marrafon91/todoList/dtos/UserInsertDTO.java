package io.github.marrafon91.todoList.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserInsertDTO(

        @NotBlank(message = "O nome é obrigatório")
        @Size(min = 3, max = 50, message = "O nome deve ter entre 3 e 50 caracteres")
        String name,

        @NotBlank(message = "O e-mail é obrigatório")
        @Email(message = "Informe um e-mail válido")
        String email,

        @NotBlank(message = "A senha é obrigatória")
        @Size(min = 6, max = 100, message = "A senha deve possuir no mínimo 6 caracteres")
        String password
) {
}
