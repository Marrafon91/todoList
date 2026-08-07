package io.github.marrafon91.todoList.controllers;

import io.github.marrafon91.todoList.dtos.CategoryDTO;
import io.github.marrafon91.todoList.services.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@Tag(name = "Categorias", description = "Operações relacionadas ao gerenciamento das Categorias.")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Operation( summary = "Lista de Categorias",
            description = "Retorna todas as categorias cadastradas."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de categorias retornada com sucesso")
    })
    @GetMapping(produces = "application/json")
    public ResponseEntity<List<CategoryDTO>> findAll() {
        List<CategoryDTO> result = categoryService.findAllCategories();
        return ResponseEntity.ok(result);
    }

    @Operation(
            summary = "Buscar Categorias por ID",
            description = "Retorna uma categoria específica pelo seu ID."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "categoria encontrada"),
            @ApiResponse(responseCode = "404", description = "categoria não encontrada")
    })
    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<CategoryDTO> findById(
            @Parameter(description = "ID da tarefa", example = "1")
            @PathVariable Long id
    ) {
        CategoryDTO dto = categoryService.findCategoryById(id);
        return ResponseEntity.ok().body(dto);
    }
}
