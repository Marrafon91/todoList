package io.github.marrafon91.todoList.dtos;

import io.github.marrafon91.todoList.entities.Priority;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record TaskUpdateDTO(

        @Schema(
                description = "Título da tarefa",
                example = "Estudar Spring Boot",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        @NotBlank(message = "O título é obrigatório")
        @Size(min = 3, max = 100)
        String title,

        @Schema(
                description = "Descrição da tarefa",
                example = "Revisar Specifications e JPA"
        )
        @Size(min = 10 ,max = 500, message = "tamanho deve ser entre 10 e 500")
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
        @NotNull(message = "A data de vencimento é obrigatória")
        LocalDate dueDate

//        boolean done
) {
}
