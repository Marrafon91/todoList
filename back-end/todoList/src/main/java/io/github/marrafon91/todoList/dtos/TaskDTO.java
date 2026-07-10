package io.github.marrafon91.todoList.dtos;

import io.github.marrafon91.todoList.entities.Priority;
import io.github.marrafon91.todoList.entities.Task;

import java.time.LocalDate;

public record TaskDTO(

        Long id,
        String title,
        String description,
        boolean done,
        LocalDate createdAt,
        LocalDate dueDate,
        Priority priority,

        CategoryDTO category
) {

    public TaskDTO(Task entity) {
        this(
                entity.getId(),
                entity.getTitle(),
                entity.getDescription(),
                entity.isDone(),
                entity.getCreatedAt(),
                entity.getDueDate(),
                entity.getPriority(),
                new CategoryDTO(entity.getCategory())
        );
    }
}