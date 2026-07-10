package io.github.marrafon91.todoList.repositories;

import io.github.marrafon91.todoList.entities.Task;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    @Override
    @EntityGraph(attributePaths = "category")
    List<Task> findAll();

    @EntityGraph(attributePaths = "category")
    List<Task> findByTitleContainingIgnoreCase(String title);
}
