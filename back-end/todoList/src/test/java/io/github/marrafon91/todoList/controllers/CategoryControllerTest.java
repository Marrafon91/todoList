package io.github.marrafon91.todoList.controllers;

import io.github.marrafon91.todoList.entities.Category;
import io.github.marrafon91.todoList.repositories.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class CategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CategoryRepository categoryRepository;

    List<Category> categories;
    private Long existingCategoryId, nonExistingCategoryId;

    @BeforeEach
    void setUp() {
        categories = categoryRepository.findAll();
        existingCategoryId = categories.getFirst().getId();
        nonExistingCategoryId = 999L;
    }

  @Test
  void categoryShouldReturnAllCategory() throws Exception {

      ResultActions result = mockMvc.perform(
              get("/api/categories")
                      .accept(MediaType.APPLICATION_JSON)
      );
      result.andExpect(status().isOk());
      result.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
      result.andExpect(jsonPath("$").isArray());
      result.andExpect(jsonPath("$.size()").value(categories.size()));
      result.andExpect(jsonPath("$.[0].id").value(categories.getFirst().getId()));
      result.andExpect(jsonPath("$.[0].name").value(categories.getFirst().getName()));
      result.andExpect(jsonPath("$.[0].color").value(categories.getFirst().getColor()));
      result.andExpect(jsonPath("$.[0].quantity").value(categories.getFirst().getTasks().size()));
    }

    @Test
    void categoryFindByIdShouldReturnCategoryWhenIdExists() throws Exception {

        ResultActions result = mockMvc.perform(
                get("/api/categories/{id}", existingCategoryId)
                        .accept(MediaType.APPLICATION_JSON)
        );
        result.andExpect(status().isOk());
        result.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
        result.andExpect(jsonPath("$.id").value(existingCategoryId));
        result.andExpect(jsonPath("$.name").value(categories.getFirst().getName()));
        result.andExpect(jsonPath("$.color").value(categories.getFirst().getColor()));
        result.andExpect(jsonPath("$.quantity").value(categories.getFirst().getTasks().size()));
    }

    @Test
    void categoryFindByIdShouldThrowResourceNotFoundExceptionWhenIdDoesNotExist() throws Exception {

        ResultActions result = mockMvc.perform(
                get("/api/categories/{id}", nonExistingCategoryId)
                        .accept(MediaType.APPLICATION_JSON)
        );
        result.andExpect(status().isNotFound());
        result.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
        result.andExpect(jsonPath("$.error").value("Categoria com ID " + nonExistingCategoryId + " não encontrada"));
    }
}
