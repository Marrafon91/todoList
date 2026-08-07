package io.github.marrafon91.todoList.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.github.marrafon91.todoList.entities.Priority;
import io.github.marrafon91.todoList.entities.Task;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record TaskDTO(

        @Schema(
                description = "Identificador único da tarefa.",
                example = "1"
        )
        Long id,

        @Schema(
                description = "Título da tarefa.",
                example = "Estudar Spring Boot"
        )
        String title,

        @Schema(
                description = "Descrição da tarefa.",
                example = "Revisar Specifications e testes unitários."
        )
        String description,

        @Schema(
                description = "Indica se a tarefa foi concluída.",
                example = "false"
        )
        boolean done,

        @Schema(
                description = "Data e hora de criação da tarefa.",
                example = "2026-08-10T14:30:00"
        )
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        LocalDateTime createdAt,

        @Schema(
                description = "Data prevista para conclusão da tarefa.",
                example = "2026-08-20"
        )
        LocalDate dueDate,

        @Schema(
                description = "Prioridade da tarefa.",
                example = "HIGH"
        )
        Priority priority,

        @Schema(
                description = "Categoria associada à tarefa."
        )
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