package io.github.marrafon91.todoList.controllers;

import io.github.marrafon91.todoList.dtos.TaskDTO;
import io.github.marrafon91.todoList.dtos.TaskInsertDTO;
import io.github.marrafon91.todoList.dtos.TaskUpdateDTO;
import io.github.marrafon91.todoList.services.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping
    public ResponseEntity<List<TaskDTO>> findAll(
            @RequestParam(required = false) String title) {
        return ResponseEntity.ok(taskService.findAll(title));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<TaskDTO> findById(@PathVariable Long id) {
        TaskDTO dto = taskService.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @PatchMapping("/{id}/done")
    public ResponseEntity<TaskDTO> toggleDone(@PathVariable Long id) {
        TaskDTO dto = taskService.toggleDone(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<TaskDTO> insert(@Valid @RequestBody TaskInsertDTO dto) {
        TaskDTO result = taskService.insert(dto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(result.id())
                .toUri();
        return ResponseEntity.created(location).body(result);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<TaskDTO> update(@PathVariable Long id, @Valid @RequestBody TaskUpdateDTO dto) {
        TaskDTO result = taskService.update(id, dto);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        taskService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
