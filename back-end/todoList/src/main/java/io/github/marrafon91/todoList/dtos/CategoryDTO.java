package io.github.marrafon91.todoList.dtos;

import io.github.marrafon91.todoList.entities.Category;

public record CategoryDTO(
        Long id,
        String name,
        String color

) {
    public CategoryDTO(Category entity) {
        this(
                entity.getId(),
                entity.getName(),
                entity.getColor()
        );
    }
}
