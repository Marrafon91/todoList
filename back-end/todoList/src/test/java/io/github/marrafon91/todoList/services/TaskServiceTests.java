package io.github.marrafon91.todoList.services;

import io.github.marrafon91.todoList.dtos.TaskDTO;
import io.github.marrafon91.todoList.dtos.TaskInsertDTO;
import io.github.marrafon91.todoList.entities.Category;
import io.github.marrafon91.todoList.entities.Priority;
import io.github.marrafon91.todoList.entities.Task;
import io.github.marrafon91.todoList.exceptions.ResourceNotFoundException;
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
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTests {

    @InjectMocks
    private TaskService taskService;

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private CategoryRepository categoryRepository;

    private long existingTaskId, nonExistingTaskId;
    private Task taskEntity;
    private TaskInsertDTO taskInsertDTO;
    private Category category;

    @BeforeEach
    public void setUp() {

        existingTaskId = 1L;
        nonExistingTaskId = 2L;

        category = new Category();
        category.setId(1L);
        category.setName("Estudos");
        category.setColor("#3B82F6");

        taskEntity = new Task();
        taskEntity.setId(existingTaskId);
        taskEntity.setTitle("Estudar Java");
        taskEntity.setDescription("Estudar testes automatizados");
        taskEntity.setDone(false);
        taskEntity.setPriority(Priority.HIGH);
        taskEntity.setDueDate(LocalDate.now());
        taskEntity.setCategory(category);

        taskInsertDTO  = new TaskInsertDTO(
                "Estudar Java",
                "Estudar testes automatizados",
                Priority.HIGH,
                1L,
                LocalDate.now()
        );
    }

    @Test
    public void findAllShouldReturnAllTaskDTOList() {

        Mockito.when(
                taskRepository.findAll(Mockito.<Specification<Task>>any())
        ).thenReturn(List.of(taskEntity));

        List<TaskDTO> result = taskService.findAll(null, null, null, null);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(1, result.size());

        TaskDTO dto = result.getFirst();

        Assertions.assertEquals(existingTaskId, dto.id());
        Assertions.assertEquals(taskEntity.getTitle(), dto.title());

        Assertions.assertNotNull(dto.category());
        Assertions.assertEquals(1L, dto.category().id());
        Assertions.assertEquals("Estudos", dto.category().name());

        Mockito.verify(taskRepository)
                .findAll(Mockito.<Specification<Task>>any());
    }

    @Test
    public void findAllShouldReturnFilteredTasks() {

        Mockito.when(
                taskRepository.findAll(Mockito.<Specification<Task>>any())
        ).thenReturn(List.of(taskEntity));

        List<TaskDTO> result = taskService.findAll(
                "Java",
                false,
                Priority.LOW,
                1L
        );

        Assertions.assertNotNull(result);
        Assertions.assertEquals(1, result.size());

        Assertions.assertEquals(existingTaskId, result.getFirst().id());

        Mockito.verify(taskRepository)
                .findAll(Mockito.<Specification<Task>>any());
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

    @Test
    public void insertShouldReturnTaskDTO() {
        Mockito.when(categoryRepository.findById(1L))
                .thenReturn(Optional.of(category));

        Mockito.when(taskRepository.save(Mockito.any(Task.class)))
                .thenReturn(taskEntity);

        TaskDTO result = taskService.insert(taskInsertDTO);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(existingTaskId, result.id());
        Assertions.assertEquals(taskInsertDTO.title(), result.title());
        Assertions.assertEquals(taskInsertDTO.description(), result.description());
        Assertions.assertEquals(taskInsertDTO.priority(), result.priority());
        Assertions.assertEquals(taskInsertDTO.dueDate(), result.dueDate());

        Assertions.assertNotNull(result.category());
        Assertions.assertEquals(1L, result.category().id());
        Assertions.assertEquals("Estudos", result.category().name());

        Mockito.verify(categoryRepository).findById(1L);
        Mockito.verify(taskRepository).save(Mockito.any(Task.class));
    }
}