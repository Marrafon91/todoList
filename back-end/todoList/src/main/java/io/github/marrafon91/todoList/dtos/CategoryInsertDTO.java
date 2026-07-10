package io.github.marrafon91.todoList.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record CategoryInsertDTO(

        @NotBlank(message = "O nome da categoria é obrigatório")
        @Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres")
        String name,

        @NotBlank(message = "A cor é obrigatória")
        @Pattern(
                regexp = "^#[0-9A-Fa-f]{6}$",
                message = "A cor deve estar no formato hexadecimal. Ex: #3B82F6"
        )
        String color
) {
}