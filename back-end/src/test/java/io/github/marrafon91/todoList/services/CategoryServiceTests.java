package io.github.marrafon91.todoList.services;

import io.github.marrafon91.todoList.dtos.CategoryDTO;
import io.github.marrafon91.todoList.entities.Category;
import io.github.marrafon91.todoList.exceptions.ResourceNotFoundException;
import io.github.marrafon91.todoList.repositories.CategoryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceTests {

    @Mock
    CategoryRepository categoryRepository;

    @InjectMocks
    CategoryService categoryService;

    private Long categoryId, nonCategoryId;
    private Category category;

    @BeforeEach
    public void setup() {

        categoryId = 1L;
        nonCategoryId = 2L;

        category = new Category();
        category.setId(categoryId);
        category.setName("Estudos");
        category.setColor("#22C55E");
    }

    @Test
    public void findAllCategoriesShouldReturnAllCategories() {
        Mockito.when(categoryRepository.findAll())
                .thenReturn(List.of(category));

        List<CategoryDTO> result = categoryService.findAllCategories();

        Assertions.assertNotNull(result);
        Assertions.assertFalse(result.isEmpty());
        Assertions.assertEquals(1, result.size());

        Assertions.assertEquals(category.getId(), result.getFirst().id());
        Assertions.assertEquals(category.getName(), result.getFirst().name());
        Assertions.assertEquals(category.getColor(), result.getFirst().color());

        Mockito.verify(categoryRepository, Mockito.times(1)).findAll();
    }

    @Test
    public void findCategoryByIdShouldReturnCategoryDTOWhenIdExist() {

        Mockito.when(categoryRepository.findById(categoryId))
                .thenReturn(Optional.of(category));

        CategoryDTO result = categoryService.findCategoryById(categoryId);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(categoryId, result.id());
        Assertions.assertEquals("Estudos", result.name());
        Assertions.assertEquals("#22C55E", result.color());

        Mockito.verify(categoryRepository).findById(categoryId);
    }

    @Test
    public void findCategoryByIdShouldThrowResourceNotFoundExceptionWhenIdDoesNotExist() {
        Mockito.when(categoryRepository.findById(nonCategoryId))
                .thenReturn(Optional.empty());

        ResourceNotFoundException exception = Assertions.assertThrows(
                ResourceNotFoundException.class,
                () -> categoryService.findCategoryById(nonCategoryId)
        );

        Assertions.assertEquals(
                "Categoria com ID " + nonCategoryId + " não encontrada",
                exception.getMessage()
        );

        Mockito.verify(categoryRepository).findById(nonCategoryId);
    }
}
