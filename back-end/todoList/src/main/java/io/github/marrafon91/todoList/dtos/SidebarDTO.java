package io.github.marrafon91.todoList.dtos;

import java.util.List;

public record SidebarDTO(
        Long totalTasks,
        Long pendingTasks,
        Long completedTasks,

        List<PrioritySummaryDTO> priorities,

        List<CategorySummaryDTO> categories
) {
}
