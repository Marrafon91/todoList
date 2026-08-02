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

    private Task task;
    private Category category;

    @BeforeEach
    public void setup() {

        category = new Category();
        category.setName("Trabalho");
        category.setColor("#2563EB");

        category = categoryRepository.save(category);

        task = new Task();
        task.setTitle("Estudar Java");
        task.setDescription("Estudar testes");
        task.setDone(false);
        task.setPriority(Priority.HIGH);
        task.setDueDate(LocalDate.now());
        task.setCategory(category);

        task = taskRepository.save(task);
    }

    @Test
    public void findAllShouldReturnTasksWithCategory() {
        List<Task> result = taskRepository.findAll();

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(26, result.size());

        Task savedTask = result.stream()
                .filter(t -> t.getTitle().equals("Estudar Java"))
                .findFirst()
                .orElseThrow();

        assertNotNull(savedTask.getCategory());

        assertEquals(category.getId(), savedTask.getCategory().getId());
        assertEquals("Trabalho", savedTask.getCategory().getName());
        assertEquals("#2563EB", savedTask.getCategory().getColor());
    }

    @Test
    public void findAllShouldReturnTasksFilteredByTitleWithCategory() {

        Specification<Task> specification = TaskSpecification.titleContains("Java");

        List<Task> result = taskRepository.findAll(specification);

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertTrue(result.stream()
                .allMatch(task -> task.getTitle().toLowerCase().contains("java")));

        Task foundTask = result.stream()
                .filter(task -> task.getTitle().equalsIgnoreCase("Estudar Java"))
                .findFirst()
                .orElseThrow();

        assertNotNull(foundTask.getCategory());
        assertEquals("Trabalho", foundTask.getCategory().getName());
        assertEquals("#2563EB", foundTask.getCategory().getColor());
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
        assertEquals(1, result.size());

        Task foundTask = result.getFirst();

        assertEquals("Estudar Java", foundTask.getTitle());
        assertFalse(foundTask.isDone());
        assertEquals(Priority.HIGH, foundTask.getPriority());
        assertNotNull(foundTask.getCategory());
        assertEquals(category.getId(), foundTask.getCategory().getId());
        assertEquals("Trabalho", foundTask.getCategory().getName());
        assertEquals("#2563EB", foundTask.getCategory().getColor());
    }
}