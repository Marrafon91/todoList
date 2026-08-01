package io.github.marrafon91.todoList.services;

import io.github.marrafon91.todoList.dtos.CategoryDTO;
import io.github.marrafon91.todoList.entities.Category;
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

@ExtendWith(MockitoExtension.class)
public class CategoryServiceTests {

    @Mock
    CategoryRepository categoryRepository;

    @InjectMocks
    CategoryService categoryService;

    private Long categoryId, nonCategoryId;
    private Category category;

    @BeforeEach
    public void setup(){

        categoryId = 1L;
        nonCategoryId = 2L;

        category = new Category();
        category.setId(categoryId);
        category.setName("Estudos");
        category.setColor("#22C55E");
    }

    @Test
    public void findAllCategoriesShouldReturnAllCategories(){
        Mockito.when(categoryRepository.findAll()).thenReturn(List.of(category));

        List<CategoryDTO> result = categoryService.findAllCategories();

        Assertions.assertNotNull(result);
        Assertions.assertFalse(result.isEmpty());
        Assertions.assertEquals(1, result.size());

        Assertions.assertEquals(category.getId(), result.getFirst().id());
        Assertions.assertEquals(category.getName(), result.getFirst().name());
        Assertions.assertEquals(category.getColor(), result.getFirst().color());

        Mockito.verify(categoryRepository, Mockito.times(1)).findAll();
    }
}
