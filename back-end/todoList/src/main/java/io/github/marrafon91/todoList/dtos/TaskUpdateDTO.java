package io.github.marrafon91.todoList.dtos;

import io.github.marrafon91.todoList.entities.Priority;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record TaskUpdateDTO(
        @NotBlank(message = "O título é obrigatório")
        @Size(min = 3, max = 100)
        String title,

        @Size(min = 10 ,max = 500, message = "tamanho deve ser entre 10 e 500")
        String description,

        @NotNull(message = "A prioridade é obrigatória")
        Priority priority,

        @NotNull(message = "A categoria é obrigatória")
        Long categoryId,

        @NotNull(message = "A data de vencimento é obrigatória")
        LocalDate dueDate,

        boolean done
) {
}
