package io.github.marrafon91.todoList.dtos;

public record CategorySummaryDTO(
        Long id,
        String name,
        String color,
        Long quantity
) {
}
