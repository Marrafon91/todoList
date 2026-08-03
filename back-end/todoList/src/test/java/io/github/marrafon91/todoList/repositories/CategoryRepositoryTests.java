package io.github.marrafon91.todoList.repositories;

import io.github.marrafon91.todoList.dtos.CategorySummaryDTO;
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
public class CategoryRepositoryTests {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private TaskRepository taskRepository;

    private Category categoryEntity;

    @BeforeEach
    public void setup() {
        categoryEntity = new Category();
        categoryEntity.setName("Trabalho");
        categoryEntity.setColor("#2563EB");

        categoryEntity = categoryRepository.save(categoryEntity);

        Task task = new Task();
        task.setTitle("Estudar Java");
        task.setDescription("Estudar testes");
        task.setDone(false);
        task.setPriority(Priority.HIGH);
        task.setDueDate(LocalDate.now());
        task.setCategory(categoryEntity);

        taskRepository.save(task);
    }

    @Test
    public void findCategorySummaryShouldReturnCategoriesOrderedByNameWithTaskCount() {

        List<CategorySummaryDTO> result = categoryRepository.findCategorySummary();

        assertNotNull(result);
        assertFalse(result.isEmpty());

        // Verifica se está ordenado pelo nome
        for (int i = 0; i < result.size() - 1; i++) {
            assertTrue(result.get(i).name().compareToIgnoreCase(result.get(i + 1).name()) <= 0);
        }

        CategorySummaryDTO summary = result.stream()
                .filter(category ->
                        category.id().equals(categoryEntity.getId()))
                .findFirst()
                .orElseThrow();

        long expected = taskRepository.findAll()
                .stream()
                .filter(task -> task.getCategory() != null && task.getCategory().getId().equals(categoryEntity.getId()))
                .count();

        assertEquals(categoryEntity.getId(), summary.id());
        assertEquals(categoryEntity.getName(), summary.name());
        assertEquals(categoryEntity.getColor(), summary.color());
        assertEquals(expected, summary.quantity());
    }
}
