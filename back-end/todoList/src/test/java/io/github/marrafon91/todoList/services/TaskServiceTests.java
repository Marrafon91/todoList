package io.github.marrafon91.todoList.services;

import io.github.marrafon91.todoList.dtos.TaskDTO;
import io.github.marrafon91.todoList.entities.Category;
import io.github.marrafon91.todoList.entities.Task;
import io.github.marrafon91.todoList.exceptions.ResourceNotFoundException;
import io.github.marrafon91.todoList.repositories.TaskRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTests {

    @InjectMocks
    private TaskService taskService;

    @Mock
    private TaskRepository taskRepository;

    private long existingTaskId;
    private long nonExistingTaskId;

    private Task taskEntity;

    @BeforeEach
    public void setUp() {

        existingTaskId = 1L;
        nonExistingTaskId = 2L;

        Category category = new Category();
        category.setId(1L);
        category.setName("Estudos");
        category.setColor("#3B82F6");

        taskEntity = new Task();
        taskEntity.setId(existingTaskId);
        taskEntity.setTitle("Estudar Java");
        taskEntity.setDescription("Estudar testes automatizados");
        taskEntity.setDone(false);
        taskEntity.setCategory(category);
    }

    @Test
    public void findByIdShouldReturnTaskDTOWhenIdExists() {

        Mockito.when(taskRepository.findById(existingTaskId))
                .thenReturn(Optional.of(taskEntity));

        TaskDTO result = taskService.findById(existingTaskId);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(existingTaskId, result.id());
        Assertions.assertEquals("Estudar Java", result.title());

        Assertions.assertNotNull(result.category());
        Assertions.assertEquals(1L, result.category().id());
        Assertions.assertEquals("Estudos", result.category().name());

        Mockito.verify(taskRepository).findById(existingTaskId);
    }

    @Test
    public void findByIdShouldThrowResourceNotFoundExceptionWhenIdDoesNotExist() {

        Mockito.when(taskRepository.findById(nonExistingTaskId))
                .thenReturn(Optional.empty());

        Assertions.assertThrows(
                ResourceNotFoundException.class,
                () -> taskService.findById(nonExistingTaskId)
        );

        Mockito.verify(taskRepository).findById(nonExistingTaskId);
    }
}