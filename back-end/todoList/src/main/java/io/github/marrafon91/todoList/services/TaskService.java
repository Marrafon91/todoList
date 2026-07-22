package io.github.marrafon91.todoList.services;

import io.github.marrafon91.todoList.dtos.TaskDTO;
import io.github.marrafon91.todoList.dtos.TaskInsertDTO;
import io.github.marrafon91.todoList.dtos.TaskUpdateDTO;
import io.github.marrafon91.todoList.entities.Category;
import io.github.marrafon91.todoList.entities.Task;
import io.github.marrafon91.todoList.exceptions.DatabaseException;
import io.github.marrafon91.todoList.exceptions.ResourceNotFoundException;
import io.github.marrafon91.todoList.repositories.CategoryRepository;
import io.github.marrafon91.todoList.repositories.TaskRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private CategoryRepository categoryRepository;

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
                .orElseThrow(() -> new ResourceNotFoundException("Tarefa com ID " + id + " não encontrada"));
    }

    @Transactional
    public TaskDTO insert(TaskInsertDTO dto) {
        Task task = new Task();
        dtoToEntityInsert(dto, task);
        task = taskRepository.save(task);
        return new TaskDTO(task);
    }

    @Transactional
    public TaskDTO update(Long id, TaskUpdateDTO dto) {
        try {
            Task task = taskRepository.getReferenceById(id);
            dtoToEntityUpdate(dto, task);
            task = taskRepository.save(task);
            return new TaskDTO(task);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Tarefa com ID " + id + " não encontrada");
        }
    }

    @Transactional
    public void delete(Long id) {

        if (!taskRepository.existsById(id)) {
            throw new ResourceNotFoundException("Tarefa com ID " + id + " não encontrada");
        }

        try {
            taskRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Não foi possível excluir a tarefa.");
        }
    }

    @Transactional
    public TaskDTO toggleDone(Long id) {
        try {
            Task task = taskRepository.getReferenceById(id);
            task.setDone(!task.isDone());
            return new TaskDTO(task);
        }
        catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(
                    "Tarefa com ID " + id + " não encontrada");
        }
    }

    private Category getCategory(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoria com ID " + id + " não encontrada"));
    }

    private void dtoToEntityInsert(TaskInsertDTO dto, Task task) {

        task.setTitle(dto.title());
        task.setDescription(dto.description());
        task.setPriority(dto.priority());
        task.setDueDate(dto.dueDate());

        task.setCategory(getCategory(dto.categoryId()));

        task.setDone(false);
    }

    private void dtoToEntityUpdate(TaskUpdateDTO dto, Task task) {

        task.setTitle(dto.title());
        task.setDescription(dto.description());
        task.setPriority(dto.priority());
        task.setDueDate(dto.dueDate());

        task.setCategory(getCategory(dto.categoryId()));

        task.setDone(dto.done());
    }
}



