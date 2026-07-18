package io.github.marrafon91.todoList.services;

import io.github.marrafon91.todoList.dtos.DashboardDTO;
import io.github.marrafon91.todoList.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class DashboardService {

    @Autowired
    private TaskRepository taskRepository;

    @Transactional(readOnly = true)
    public DashboardDTO getDashboard() {
        String greeting = "Olá como você está ?";
        LocalDate currentDate = LocalDate.now();
        Long totalTasks = taskRepository.count();
        Long pendingTasks = taskRepository.countPendingTasks();
        Long completedTasks = taskRepository.countCompletedTasks();
        Long highPriorityTasks = taskRepository.countHighPriorityTasks();

        return new DashboardDTO(
                greeting,
                currentDate,
                totalTasks,
                pendingTasks,
                completedTasks,
                highPriorityTasks
        );
    }
}
