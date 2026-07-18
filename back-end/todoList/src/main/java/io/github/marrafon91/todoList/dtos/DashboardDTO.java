package io.github.marrafon91.todoList.dtos;

import java.time.LocalDate;

public record DashboardDTO(
        String greeting,
        LocalDate currentDate,
        Long totalTasks,
        Long pendingTasks,
        Long completedTasks,
        Long highPriorityTasks
) {
}
