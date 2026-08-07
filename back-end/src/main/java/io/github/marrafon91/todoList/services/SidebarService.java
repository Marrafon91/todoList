package io.github.marrafon91.todoList.services;

import io.github.marrafon91.todoList.dtos.PrioritySummaryDTO;
import io.github.marrafon91.todoList.dtos.SidebarDTO;
import io.github.marrafon91.todoList.entities.Priority;
import io.github.marrafon91.todoList.repositories.CategoryRepository;
import io.github.marrafon91.todoList.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SidebarService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    public SidebarDTO findSidebar() {

        List<PrioritySummaryDTO> priorities = taskRepository.findPrioritySummaryHigh()
                .stream()
                .filter(p -> p.priority() == Priority.HIGH)
                .map(p -> new PrioritySummaryDTO(
                        p.priority(),
                        "Alta prioridade",
                        p.quantity()))
                .toList();

        return new SidebarDTO(
                taskRepository.count(),
                taskRepository.countByDoneFalse(),
                taskRepository.countByDoneTrue(),
                priorities,
                categoryRepository.findCategorySummary()
        );
    }
}