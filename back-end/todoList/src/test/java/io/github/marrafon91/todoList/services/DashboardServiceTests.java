package io.github.marrafon91.todoList.services;

import io.github.marrafon91.todoList.dtos.DashboardDTO;
import io.github.marrafon91.todoList.repositories.TaskRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class DashboardServiceTests {

    @InjectMocks
    private DashboardService dashboardService;

    @Mock
    private TaskRepository taskRepository;

    @BeforeEach
    public void setUp() {
        Mockito.when(taskRepository.count()).thenReturn(10L);
        Mockito.when(taskRepository.countPendingTasks()).thenReturn(4L);
        Mockito.when(taskRepository.countCompletedTasks()).thenReturn(6L);
        Mockito.when(taskRepository.countHighPriorityTasks()).thenReturn(2L);
    }

    @Test
    public void getDashboardShouldReturnDashboardDTO() {

        DashboardDTO result = dashboardService.getDashboard();

        Assertions.assertNotNull(result);

        Assertions.assertEquals("Olá, como você está?", result.greeting());
        Assertions.assertNotNull(result.currentDate());

        Assertions.assertEquals(4L, result.pendingTasks());
        Assertions.assertEquals(2L, result.highPriorityTasks());

        Assertions.assertNotNull(result.cards());
        Assertions.assertEquals(4, result.cards().size());

        Assertions.assertEquals("Total", result.cards().get(0).title());
        Assertions.assertEquals(10L, result.cards().get(0).value());

        Assertions.assertEquals("Pendentes", result.cards().get(1).title());
        Assertions.assertEquals(4L, result.cards().get(1).value());

        Assertions.assertEquals("Concluídas", result.cards().get(2).title());
        Assertions.assertEquals(6L, result.cards().get(2).value());

        Assertions.assertEquals("Alta prioridade", result.cards().get(3).title());
        Assertions.assertEquals(2L, result.cards().get(3).value());

        Mockito.verify(taskRepository).count();
        Mockito.verify(taskRepository).countPendingTasks();
        Mockito.verify(taskRepository).countCompletedTasks();
        Mockito.verify(taskRepository).countHighPriorityTasks();
    }
}