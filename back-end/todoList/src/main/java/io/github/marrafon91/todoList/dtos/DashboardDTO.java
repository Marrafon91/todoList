package io.github.marrafon91.todoList.dtos;

import java.time.LocalDate;
import java.util.List;

public record DashboardDTO(
        String greeting,
        LocalDate currentDate,
        Long pendingTasks,
        Long highPriorityTasks,
        List<DashboardCardDTO> cards
) {
}
