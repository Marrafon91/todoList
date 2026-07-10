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

        Long userId,
        String userName,

        Long categoryId,
        String categoryName,
        String categoryColor

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

                entity.getUser().getId(),
                entity.getUser().getName(),

                entity.getCategory().getId(),
                entity.getCategory().getName(),
                entity.getCategory().getColor()
        );
    }
}