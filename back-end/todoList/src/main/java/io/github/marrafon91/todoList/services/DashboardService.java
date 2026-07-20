package io.github.marrafon91.todoList.services;

import io.github.marrafon91.todoList.dtos.DashboardCardDTO;
import io.github.marrafon91.todoList.dtos.DashboardDTO;
import io.github.marrafon91.todoList.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class DashboardService {

    @Autowired
    private TaskRepository taskRepository;

    @Transactional(readOnly = true)
    public DashboardDTO getDashboard() {

        Long totalTasks = taskRepository.count();
        Long pendingTasks = taskRepository.countPendingTasks();
        Long completedTasks = taskRepository.countCompletedTasks();
        Long highPriorityTasks = taskRepository.countHighPriorityTasks();

        List<DashboardCardDTO> cards = List.of(
                new DashboardCardDTO("Total", totalTasks),
                new DashboardCardDTO("Pendentes", pendingTasks),
                new DashboardCardDTO("Concluídas", completedTasks),
                new DashboardCardDTO("Alta prioridade", highPriorityTasks)
        );

        return new DashboardDTO(
                "Olá, como você está?",
                LocalDate.now(),
                pendingTasks,
                highPriorityTasks,
                cards
        );
    }

}
