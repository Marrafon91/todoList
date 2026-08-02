package io.github.marrafon91.todoList.repositories;

import io.github.marrafon91.todoList.entities.Category;
import io.github.marrafon91.todoList.entities.Priority;
import io.github.marrafon91.todoList.entities.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

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
}