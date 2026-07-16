package io.github.marrafon91.todoList.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.github.marrafon91.todoList.entities.Priority;
import io.github.marrafon91.todoList.entities.Task;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record TaskDTO(

        Long id,
        String title,
        String description,
        boolean done,
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        LocalDateTime createdAt,
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