package io.github.marrafon91.todoList.dtos;

import io.github.marrafon91.todoList.entities.Priority;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record TaskInsertDTO(
        @Size(min = 3, max = 100, message = "O título é obrigatório, deve ter de 3 a 100 caracteres")
        String title,
        @Size(min = 10, max = 500, message = "A Descrição deve ter de 10 a 500 caracteres")
        String description,
        @NotNull(message = "A prioridade é obrigatória")
        Priority priority,
        @NotNull(message = "A categoria é obrigatória")
        Long categoryId,
        @FutureOrPresent(message = "A data de vencimento é obrigatória, deve ser uma data futura")
        LocalDate dueDate
) {
}
