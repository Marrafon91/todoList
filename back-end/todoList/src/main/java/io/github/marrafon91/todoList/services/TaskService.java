package io.github.marrafon91.todoList.services;

import io.github.marrafon91.todoList.dtos.TaskDTO;
import io.github.marrafon91.todoList.entities.Task;
import io.github.marrafon91.todoList.exceptions.ResourceNotFound;
import io.github.marrafon91.todoList.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Transactional(readOnly = true)
    public List<TaskDTO> findAll(String title) {
        List<Task> tasks;

        if (title == null || title.isBlank()) {
            tasks = taskRepository.findAll();
        } else {
            tasks = taskRepository.findByTitleContainingIgnoreCase(title);
        }
        return tasks.stream().map(TaskDTO::new).toList();
    }

    @Transactional(readOnly = true)
    public TaskDTO findById(Long id) {
        return taskRepository.findById(id)
                .map(TaskDTO::new)
                .orElseThrow(() -> new ResourceNotFound("Tarefa com ID " + id + " não encontrada"));
    }
}
