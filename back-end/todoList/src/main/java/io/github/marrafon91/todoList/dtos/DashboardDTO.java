package io.github.marrafon91.todoList.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;
import java.util.List;

public record DashboardDTO(

        @Schema(
                description = "Mensagem de saudação exibida no dashboard.",
                example = "Olá, como você está?"
        )
        String greeting,

        @Schema(
                description = "Data atual do sistema.",
                example = "2026-08-15"
        )
        LocalDate currentDate,

        @Schema(
                description = "Quantidade de tarefas pendentes.",
                example = "5"
        )
        Long pendingTasks,

        @Schema(
                description = "Quantidade de tarefas com prioridade alta.",
                example = "2"
        )
        Long highPriorityTasks,

        @Schema(
                description = "Lista de cards com os indicadores do dashboard."
        )
        List<DashboardCardDTO> cards
) {
}
