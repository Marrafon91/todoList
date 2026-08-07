package io.github.marrafon91.todoList.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

public record CategorySummaryDTO(

        @Schema(
                description = "ID da categoria",
                example = "2"
        )
        Long id,

        @Schema(
                description = "Descrição da categoria",
                example = "Casa"
        )
        String name,

        @Schema(
                description = "Hexadecimal da cor",
                example = "#EF4444"
        )
        String color,

        @Schema(
                description = "Quantidade de Tarefas por Categoria",
                example = "Casa tem 9 tarefas"
        )
        Long quantity
) {
}
