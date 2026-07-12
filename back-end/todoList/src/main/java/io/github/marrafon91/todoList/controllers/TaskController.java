package io.github.marrafon91.todoList.controllers;

import io.github.marrafon91.todoList.dtos.TaskDTO;
import io.github.marrafon91.todoList.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
