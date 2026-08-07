package io.github.marrafon91.todoList.dtos;

import io.github.marrafon91.todoList.entities.Category;
import io.swagger.v3.oas.annotations.media.Schema;

public record CategoryDTO(

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
        Integer quantity

) {
    public CategoryDTO(Category entity) {
        this(
                entity.getId(),
                entity.getName(),
                entity.getColor(),
                entity.getTasks().size()
        );
    }
}
