package io.github.marrafon91.todoList.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

public record CategorySummaryDTO(

        @Schema(
                description = "Identificador único da categoria.",
                example = "2"
        )
        Long id,

        @Schema(
                description = "Nome da categoria.",
                example = "Casa"
        )
        String name,

        @Schema(
                description = "Cor da categoria em formato hexadecimal.",
                example = "#EF4444"
        )
        String color,

        @Schema(
                description = "Quantidade de tarefas vinculadas à categoria.",
                example = "9"
        )
        Long quantity
) {
}
