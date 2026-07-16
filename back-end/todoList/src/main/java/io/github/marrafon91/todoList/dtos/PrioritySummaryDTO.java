package io.github.marrafon91.todoList.dtos;

import io.github.marrafon91.todoList.entities.Priority;

public record PrioritySummaryDTO(
        Priority priority,
        String label,
        Long quantity
) {
}
