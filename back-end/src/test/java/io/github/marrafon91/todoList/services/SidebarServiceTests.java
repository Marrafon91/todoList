package io.github.marrafon91.todoList.services;

import io.github.marrafon91.todoList.dtos.PrioritySummaryDTO;
import io.github.marrafon91.todoList.dtos.SidebarDTO;
import io.github.marrafon91.todoList.entities.Priority;
import io.github.marrafon91.todoList.repositories.CategoryRepository;
import io.github.marrafon91.todoList.repositories.TaskRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class SidebarServiceTests {

    @InjectMocks
    private SidebarService sidebarService;

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @BeforeEach
    public void setup() {
        Mockito.when(taskRepository.findPrioritySummaryHigh())
                .thenReturn(List.of(
                        new PrioritySummaryDTO(
                                Priority.HIGH,
                                "Alta prioridade",
                                3L
                        ),
                        new PrioritySummaryDTO(
                                Priority.MEDIUM,
                                "Média prioridade",
                                5L
                        ),
                        new PrioritySummaryDTO(
                                Priority.LOW,
                                "Baixa prioridade",
                                2L
                        )
                ));

        Mockito.when(taskRepository.count()).thenReturn(10L);
        Mockito.when(taskRepository.countByDoneFalse()).thenReturn(4L);
        Mockito.when(taskRepository.countByDoneTrue()).thenReturn(6L);
        Mockito.when(categoryRepository.findCategorySummary()).thenReturn(List.of());
    }

    @Test
    public void findSidebarShouldReturnSidebarDTO() {

        SidebarDTO result = sidebarService.findSidebar();

        Assertions.assertNotNull(result);

        Assertions.assertEquals(10L, result.totalTasks());
        Assertions.assertEquals(4L, result.pendingTasks());
        Assertions.assertEquals(6L, result.completedTasks());

        Assertions.assertNotNull(result.priorities());
        Assertions.assertEquals(1, result.priorities().size());

        PrioritySummaryDTO priority = result.priorities().getFirst();

        Assertions.assertEquals(Priority.HIGH, priority.priority());

        Assertions.assertEquals("Alta prioridade", priority.label());

        Assertions.assertEquals(3L, priority.quantity());

        Assertions.assertNotNull(result.categories());

        Mockito.verify(taskRepository).findPrioritySummaryHigh();
        Mockito.verify(taskRepository).count();
        Mockito.verify(taskRepository).countByDoneFalse();
        Mockito.verify(taskRepository).countByDoneTrue();
        Mockito.verify(categoryRepository).findCategorySummary();
    }

    @Test
    public void findSidebarShouldReturnOnlyHighPriority() {

        SidebarDTO result = sidebarService.findSidebar();

        Assertions.assertNotNull(result);
        Assertions.assertNotNull(result.priorities());

        Assertions.assertEquals(1, result.priorities().size());

        PrioritySummaryDTO priority = result.priorities().getFirst();

        Assertions.assertEquals(Priority.HIGH, priority.priority());
        Assertions.assertEquals("Alta prioridade", priority.label());
        Assertions.assertEquals(3L, priority.quantity());

        Mockito.verify(taskRepository).findPrioritySummaryHigh();
    }
}