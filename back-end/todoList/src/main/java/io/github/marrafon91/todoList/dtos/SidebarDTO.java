package io.github.marrafon91.todoList.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public record SidebarDTO(

        @Schema(
                description = "Quantidade total de tarefas cadastradas.",
                example = "26"
        )
        Long totalTasks,

        @Schema(
                description = "Quantidade de tarefas pendentes.",
                example = "15"
        )
        Long pendingTasks,

        @Schema(
                description = "Quantidade de tarefas concluídas.",
                example = "11"
        )
        Long completedTasks,

        @Schema(
                description = "Resumo das tarefas por prioridade."
        )
        List<PrioritySummaryDTO> priorities,

        @Schema(
                description = "Resumo das categorias com suas respectivas quantidades de tarefas."
        )
        List<CategorySummaryDTO> categories
) {
}
