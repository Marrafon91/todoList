package io.github.marrafon91.todoList.services;

import io.github.marrafon91.todoList.dtos.CategoryDTO;
import io.github.marrafon91.todoList.entities.Category;
import io.github.marrafon91.todoList.exceptions.ResourceNotFoundException;
import io.github.marrafon91.todoList.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    public List<CategoryDTO> findAllCategories() {
        List<Category> result = categoryRepository.findAll();
        return result.stream().map(CategoryDTO::new).toList();
    }

    @Transactional(readOnly = true)
    public CategoryDTO findCategoryById(Long id) {
        return categoryRepository.findById(id)
                .map(CategoryDTO::new)
                .orElseThrow(() -> new ResourceNotFoundException("Categoria com ID " + id + " não encontrada"));
    }

}
