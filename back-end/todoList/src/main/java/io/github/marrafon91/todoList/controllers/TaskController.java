package io.github.marrafon91.todoList.controllers;

import io.github.marrafon91.todoList.dtos.TaskDTO;
import io.github.marrafon91.todoList.dtos.TaskInsertDTO;
import io.github.marrafon91.todoList.dtos.TaskUpdateDTO;
import io.github.marrafon91.todoList.entities.Priority;
import io.github.marrafon91.todoList.services.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@Tag(name = "Tarefas", description = "Operações relacionadas ao gerenciamento das tarefas.")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Operation( summary = "Listar tarefas",
            description = "Retorna todas as tarefas cadastradas podendo ser filtradas por título, status, prioridade e categoria."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de tarefas retornada com sucesso")
    })
    @GetMapping(produces = "application/json")
    public ResponseEntity<List<TaskDTO>> findAll(

            @Parameter(description = "Filtrar pelo título")
            @RequestParam(required = false) String title,

            @Parameter(description = "Filtrar por tarefa concluída")
            @RequestParam(required = false) Boolean done,

            @Parameter(description = "Filtrar por prioridade")
            @RequestParam(required = false) Priority priority,

            @Parameter(description = "Filtrar por categoria")
            @RequestParam(required = false) Long categoryId
    ) {
        return ResponseEntity.ok(
                taskService.findAll(
                        title,
                        done,
                        priority,
                        categoryId
                )
        );
    }

    @Operation(
            summary = "Buscar tarefa por ID",
            description = "Retorna uma tarefa específica pelo seu identificador."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Tarefa encontrada"),
            @ApiResponse(responseCode = "404", description = "Tarefa não encontrada")
    })
    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<TaskDTO> findById(
            @Parameter(description = "ID da tarefa", example = "1")
            @PathVariable Long id
    ) {
        TaskDTO dto = taskService.findById(id);
        return ResponseEntity.ok(dto);
    }

    @Operation(
            summary = "Alterar status da tarefa",
            description = "Alterna automaticamente entre concluída e pendente."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Status atualizado"),
            @ApiResponse(responseCode = "404", description = "Tarefa não encontrada")
    })
    @PatchMapping(value = "/{id}/done", produces = "application/json")
    public ResponseEntity<TaskDTO> toggleDone(
            @Parameter(description = "ID da tarefa", example = "1")
            @PathVariable Long id
    ) {
        TaskDTO dto = taskService.toggleDone(id);
        return ResponseEntity.ok(dto);
    }

    @Operation( summary = "Cadastrar tarefa",description = "Cadastra uma nova tarefa no sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Tarefa criada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Categoria não encontrada"),
            @ApiResponse(responseCode = "422", description = "Erro de validação")
    })
    @PostMapping(produces = "application/json")
    public ResponseEntity<TaskDTO> insert(
            @RequestBody(
                    description = "Dados para cadastro da tarefa",
                    required = true,
                    content = @Content(schema = @Schema(implementation = TaskInsertDTO.class))
            )
            @Valid
            @org.springframework.web.bind.annotation.RequestBody TaskInsertDTO dto

    ) {
        TaskDTO result = taskService.insert(dto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(result.id())
                .toUri();

        return ResponseEntity.created(location).body(result);
    }

    @Operation(
            summary = "Atualizar tarefa",
            description = "Atualiza todos os dados de uma tarefa."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Tarefa atualizada"),
            @ApiResponse(responseCode = "404", description = "Tarefa não encontrada"),
            @ApiResponse(responseCode = "422", description = "Erro de validação")
    })
    @PutMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<TaskDTO> update(

            @Parameter(description = "ID da tarefa", example = "1")
            @PathVariable Long id,

            @RequestBody(
                    description = "Dados para atualização da tarefa",
                    required = true,
                    content = @Content(schema = @Schema(implementation = TaskUpdateDTO.class))
            )
            @Valid
            @org.springframework.web.bind.annotation.RequestBody TaskUpdateDTO dto

    ) {

        TaskDTO result = taskService.update(id, dto);
        return ResponseEntity.ok(result);
    }

    @Operation(
            summary = "Excluir tarefa",
            description = "Remove uma tarefa pelo ID."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Tarefa removida"),
            @ApiResponse(responseCode = "404", description = "Tarefa não encontrada")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(
            @Parameter(description = "ID da tarefa", example = "1")
            @PathVariable Long id
    ) {
        taskService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Excluir todas as tarefas",
            description = "Remove todas as tarefas cadastradas."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Todas as tarefas removidas")
    })
    @DeleteMapping("/delete-all")
    public ResponseEntity<Void> deleteAll() {

        taskService.deleteAll();
        return ResponseEntity.noContent().build();
    }
}