package io.github.marrafon91.todoList.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

public record DashboardCardDTO(

        @Schema(
                description = "Título do indicador exibido no card do dashboard.",
                example = "Concluídas"
        )
        String title,

        @Schema(
                description = "Quantidade correspondente ao indicador do card.",
                example = "9"
        )
        Long value
) {
}
