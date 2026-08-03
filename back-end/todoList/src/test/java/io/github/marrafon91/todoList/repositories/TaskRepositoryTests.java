package io.github.marrafon91.todoList.repositories;

import io.github.marrafon91.todoList.entities.Category;
import io.github.marrafon91.todoList.entities.Priority;
import io.github.marrafon91.todoList.entities.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import io.github.marrafon91.todoList.specifications.TaskSpecification;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class TaskRepositoryTests {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    private Category category;
    private Task task;

    @BeforeEach
    public void setup() {
        category = new Category();
        category.setName("Trabalho");
        category.setColor("#2563EB");

        category = categoryRepository.save(category);

        // Tarefa pendente
        task = new Task();
        task.setTitle("Estudar Java");
        task.setDescription("Estudar testes");
        task.setDone(false);
        task.setPriority(Priority.HIGH);
        task.setDueDate(LocalDate.now());
        task.setCategory(category);

        task = taskRepository.save(task);

        // Tarefa concluída
        Task completedTask = new Task();
        completedTask.setTitle("Estudar Spring Boot");
        completedTask.setDescription("Estudar JPA");
        completedTask.setDone(true);
        completedTask.setPriority(Priority.MEDIUM);
        completedTask.setDueDate(LocalDate.now());
        completedTask.setCategory(category);

        taskRepository.save(completedTask);
    }

    @Test
    public void findAllShouldReturnTasksWithCategory() {
        List<Task> result = taskRepository.findAll();

        long expected = taskRepository.findAll().size();

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(expected, result.size());

        Task savedTask = result.stream()
                .filter(t -> t.getTitle().equals(task.getTitle()))
                .findFirst()
                .orElseThrow();

        assertNotNull(savedTask.getCategory());

        assertEquals(category.getId(), savedTask.getCategory().getId());
        assertEquals(category.getName(), savedTask.getCategory().getName());
        assertEquals(category.getColor(), savedTask.getCategory().getColor());
    }

    @Test
    public void findAllShouldReturnTasksFilteredByTitleWithCategory() {

        Specification<Task> specification =
                TaskSpecification.titleContains("Java");

        List<Task> result = taskRepository.findAll(specification);

        assertNotNull(result);
        assertFalse(result.isEmpty());

        assertTrue(result.stream()
                .allMatch(t -> t.getTitle()
                        .toLowerCase()
                        .contains("java")));

        Task foundTask = result.stream()
                .filter(t -> t.getId().equals(task.getId()))
                .findFirst()
                .orElseThrow();

        assertEquals(task.getId(), foundTask.getId());
        assertEquals(task.getTitle(), foundTask.getTitle());

        assertNotNull(foundTask.getCategory());

        assertEquals(category.getId(), foundTask.getCategory().getId());
        assertEquals(category.getName(), foundTask.getCategory().getName());
        assertEquals(category.getColor(), foundTask.getCategory().getColor());
    }

    @Test
    public void findAllShouldReturnTasksFilteredByMultipleSpecifications() {

        Specification<Task> specification =
                Specification.where(TaskSpecification.titleContains("Java"))
                        .and(TaskSpecification.done(false))
                        .and(TaskSpecification.priority(Priority.HIGH))
                        .and(TaskSpecification.category(category.getId()));

        List<Task> result = taskRepository.findAll(specification);

        assertNotNull(result);
        assertFalse(result.isEmpty());

        Task foundTask = result.stream()
                .filter(t -> t.getId().equals(task.getId()))
                .findFirst()
                .orElseThrow();

        assertEquals(task.getId(), foundTask.getId());
        assertEquals(task.getTitle(), foundTask.getTitle());
        assertFalse(foundTask.isDone());
        assertEquals(Priority.HIGH, foundTask.getPriority());

        assertNotNull(foundTask.getCategory());
        assertEquals(category.getId(), foundTask.getCategory().getId());
        assertEquals(category.getName(), foundTask.getCategory().getName());
        assertEquals(category.getColor(), foundTask.getCategory().getColor());
    }

    @Test
    public void countByDoneFalseShouldReturnNumberOfNotDoneTasks() {

        Long result = taskRepository.countByDoneFalse();

        long expected = taskRepository.findAll()
                .stream()
                .filter(task -> !task.isDone())
                .count();

        assertNotNull(result);
        assertEquals(expected, result);
    }

    @Test
    public void countByDoneTrueShouldReturnNumberOfDoneTasks() {

        Long result = taskRepository.countByDoneTrue();

        long expected = taskRepository.findAll()
                .stream()
                .filter(Task::isDone)
                .count();

        assertNotNull(result);
        assertEquals(expected, result);
    }

    @Test
    public void countPendingTasksShouldReturnNumberOfPendingTasks() {

        Long result = taskRepository.countPendingTasks();

        long expected = taskRepository.findAll()
                .stream()
                .filter(task -> !task.isDone())
                .count();

        assertNotNull(result);
        assertEquals(expected, result);
    }

    @Test
    public void countCompletedTasksShouldReturnNumberOfDoneTasks() {

        Long result = taskRepository.countCompletedTasks();

        long expected = taskRepository.findAll()
                .stream()
                .filter(Task::isDone)
                .count();

        assertNotNull(result);
        assertEquals(expected, result);
    }

    @Test
    public void countHighPriorityTasksShouldReturnNumberOfHighPriorityTasks() {

        Long result = taskRepository.countHighPriorityTasks();

        long expected = taskRepository.findAll()
                .stream()
                .filter(t-> t.getPriority() == Priority.HIGH)
                .count();

        assertNotNull(result);
        assertEquals(expected, result);
    }
}