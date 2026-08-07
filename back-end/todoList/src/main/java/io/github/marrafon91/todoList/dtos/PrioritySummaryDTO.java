package io.github.marrafon91.todoList.dtos;

import io.github.marrafon91.todoList.entities.Priority;
import io.swagger.v3.oas.annotations.media.Schema;

public record PrioritySummaryDTO(

        @Schema(
                description = "Prioridade da tarefa.",
                example = "HIGH"
        )
        Priority priority,

        @Schema(
                description = "Descrição amigável da prioridade.",
                example = "Alta prioridade"
        )
        String label,

        @Schema(
                description = "Quantidade de tarefas dessa prioridade.",
                example = "7"
        )
        Long quantity
) {
}
