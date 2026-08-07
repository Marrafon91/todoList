package io.github.marrafon91.todoList.dtos;

import io.github.marrafon91.todoList.entities.Priority;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record TaskInsertDTO(

        @Schema(
                description = "Título da tarefa",
                example = "Estudar Spring Boot",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        @Size(min = 3, max = 100, message = "O título é obrigatório, deve ter de 3 a 100 caracteres")
        String title,

        @Schema(
                description = "Descrição da tarefa",
                example = "Revisar Specifications e JPA"
        )
        @Size(min = 10, max = 500, message = "A Descrição deve ter de 10 a 500 caracteres")
        String description,

        @Schema(
                description = "Prioridade da tarefa",
                example = "HIGH"
        )
        @NotNull(message = "A prioridade é obrigatória")
        Priority priority,

        @Schema(
                description = "ID da categoria",
                example = "2"
        )
        @NotNull(message = "A categoria é obrigatória")
        Long categoryId,

        @Schema(
                description = "Data prevista para conclusão da tarefa.",
                example = "2027-08-15"
        )
        @FutureOrPresent(message = "A data de vencimento é obrigatória, deve ser uma data futura")
        LocalDate dueDate
) {
}
